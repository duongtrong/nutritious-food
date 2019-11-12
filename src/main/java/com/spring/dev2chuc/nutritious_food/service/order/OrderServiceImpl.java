package com.spring.dev2chuc.nutritious_food.service.order;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyOrderDetailResponse;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderResponse;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ComboRepository comboRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderResponse> getAllByUser(User user) {
        return orderRepository.findAllByUser(user).stream().map(x -> new OrderResponse(x)).collect(Collectors.toList());
    }

    @Override
    public OrderResponse saveOrderByUser(User user, List<OrderRequest> orderRequests) {

        Order order = new Order(user, (float) 0);
        Order orderSave = orderRepository.save(order);
        float totalPrice = 0;

        Set<OrderDetail> orderDetails = new HashSet<>();
        for (OrderRequest orderRequest : orderRequests) {
            OrderDetail orderDetailCurrent = null;
            if (orderRequest.getFoodId() != null) {
                Food food = foodRepository.findByIdAndStatus(orderRequest.getFoodId(), Status.ACTIVE.getValue());
                if (food == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        food,
                        orderRequest.getQuantity(),
                        orderRequest.getPrice()
                );

            } else if (orderRequest.getComboId() != null) {
                Combo combo = comboRepository.findByStatusAndId(Status.ACTIVE.getValue(), orderRequest.getComboId());
                if (combo == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        combo,
                        orderRequest.getQuantity(),
                        orderRequest.getPrice()
                );
            } else if (orderRequest.getScheduleId() != null) {
                Schedule schedule = scheduleRepository.findByStatusAndId(Status.ACTIVE.getValue(), orderRequest.getScheduleId());
                if (schedule == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        schedule,
                        orderRequest.getQuantity(),
                        orderRequest.getPrice()
                );
            } else {
                return null;
            }

            OrderDetail orderDetail = orderDetailRepository.save(orderDetailCurrent);
//            onlyOrderDetailResponse = new OnlyOrderDetailResponse(orderDetail);
//            onlyOrderDetailResponses.add(onlyOrderDetailResponse);
            orderDetails.add(orderDetail);
            System.out.println(orderDetail.getType());
            totalPrice += orderRequest.getPrice() * orderRequest.getQuantity();
        }
        orderSave.setTotalPrice(totalPrice);
        orderSave.setOrderDetails(orderDetails);
        Order orderSavePrice = orderRepository.save(orderSave);
        System.out.println(orderSavePrice.getOrderDetails().size());
        return new OrderResponse(orderSavePrice);
    }

    @Override
    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(null);
        if (order == null) {
            return null;
        }

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderAndStatus(order, Status.ACTIVE.getValue());
        Set<OnlyOrderDetailResponse> onlyOrderDetailResponses = new HashSet<>();
        for (OrderDetail orderDetail : orderDetails) {
            OnlyOrderDetailResponse onlyOrderDetailResponse = new OnlyOrderDetailResponse(orderDetail);
            onlyOrderDetailResponses.add(onlyOrderDetailResponse);
        }
        OrderResponse orderResponse = new OrderResponse(order, onlyOrderDetailResponses);
        return orderResponse;
    }
}
