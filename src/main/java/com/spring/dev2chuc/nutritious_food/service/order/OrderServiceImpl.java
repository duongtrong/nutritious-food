package com.spring.dev2chuc.nutritious_food.service.order;

import com.spring.dev2chuc.nutritious_food.config.VnPayConfig;
import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.OrderDetailRequest;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDTO;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDetailDTO;
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
    AddressRepository addressRepository;

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
    public List<OrderDTO> getAllByUser(User user) {
        List<Address> addresses = addressRepository.findAllByUserAndStatus(user, Status.ACTIVE.getValue());
        return orderRepository.findAllByAddressIn(addresses).stream().map(x -> new OrderDTO(x, true)).collect(Collectors.toList());
    }

    @Override
    public Order saveOrderByUser(OrderRequest orderRequest) {
        System.out.println(orderRequest.getAddressId());
        Address address = addressRepository.findByIdAndStatus(orderRequest.getAddressId(), Status.ACTIVE.getValue());
        if (address == null) return null;
        Order order = new Order(address, (float) 0, orderRequest.getNote(), orderRequest.getType());
        order.setCode(VnPayConfig.getRandomNumber(8));
        Order orderSave = orderRepository.save(order);
        float totalPrice = 0;

        Set<OrderDetail> orderDetails = new HashSet<>();
        for (OrderDetailRequest orderDetailRequest : orderRequest.getOrderDetails()) {
            OrderDetail orderDetailCurrent = null;
            if (orderDetailRequest.getFoodId() != null) {
                Food food = foodRepository.findByIdAndStatus(orderDetailRequest.getFoodId(), Status.ACTIVE.getValue());
                if (food == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        food,
                        orderDetailRequest.getQuantity(),
                        food.getPrice()
                );

            } else if (orderDetailRequest.getComboId() != null) {
                Combo combo = comboRepository.findByStatusAndId(Status.ACTIVE.getValue(), orderDetailRequest.getComboId());
                if (combo == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        combo,
                        orderDetailRequest.getQuantity(),
                        combo.getPrice()
                );
            } else if (orderDetailRequest.getScheduleId() != null) {
                Schedule schedule = scheduleRepository.findByStatusAndId(Status.ACTIVE.getValue(), orderDetailRequest.getScheduleId());
                if (schedule == null) return null;
                orderDetailCurrent = new OrderDetail(
                        orderSave,
                        schedule,
                        orderDetailRequest.getQuantity(),
                        schedule.getPrice()
                );
            } else {
                return null;
            }

            OrderDetail orderDetail = orderDetailRepository.save(orderDetailCurrent);

            orderDetails.add(orderDetail);
            System.out.println(orderDetail.getType());
            totalPrice += orderDetailCurrent.getPrice() * orderDetailRequest.getQuantity();
        }
        orderSave.setTotalPrice(totalPrice);
        orderSave.setOrderDetails(orderDetails);
        return orderRepository.save(orderSave);
    }

    @Override
    public OrderDTO getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(null);
        if (order == null) {
            return null;
        }

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderAndStatus(order, Status.ACTIVE.getValue());
        Set<OrderDetailDTO> onlyOrderDetailResponses = new HashSet<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDTO onlyOrderDetailResponse = new OrderDetailDTO(orderDetail, true);
            onlyOrderDetailResponses.add(onlyOrderDetailResponse);
        }

        return new OrderDTO(order, true);
    }
}
