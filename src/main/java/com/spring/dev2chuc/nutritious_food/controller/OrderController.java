package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.*;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/order")
public class OrderController {
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

    @GetMapping
    public ResponseEntity<?> getListByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            List<Order> orderList = orderRepository.findAllByUser(user);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", orderList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody List<OrderRequest> orderRequests) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            Order order = new Order(user, (float) 0);
            Order orderSave = orderRepository.save(order);
            float totalPrice = 0;

            Set<OnlyOrderDetailResponse> onlyOrderDetailResponses = new HashSet<>();
            for (OrderRequest orderRequest : orderRequests) {
                System.out.println(orderRequest.getFoodId());
                OnlyOrderDetailResponse onlyOrderDetailResponse = null;
                OrderDetail orderDetailCurrent = null;
                if (orderRequest.getFoodId() != null) {
                    Food food = foodRepository.findById(orderRequest.getFoodId()).orElseThrow(null);
                    orderDetailCurrent = new OrderDetail(
                            orderSave,
                            food,
                            orderRequest.getQuantity(),
                            orderRequest.getPrice()
                    );

                } else if (orderRequest.getComboId() != null) {
                    Combo combo = comboRepository.findById(orderRequest.getComboId()).orElseThrow(null);
                    orderDetailCurrent = new OrderDetail(
                            orderSave,
                            combo,
                            orderRequest.getQuantity(),
                            orderRequest.getPrice()
                    );
                } else if (orderRequest.getScheduleId() != null) {
                    Schedule schedule = scheduleRepository.findById(orderRequest.getScheduleId()).orElseThrow(null);
                    orderDetailCurrent = new OrderDetail(
                            orderSave,
                            schedule,
                            orderRequest.getQuantity(),
                            orderRequest.getPrice()
                    );
                } else {
                    return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Data not match"), HttpStatus.BAD_REQUEST);
                }
                OrderDetail orderDetail = orderDetailRepository.save(orderDetailCurrent);
                onlyOrderDetailResponse = new OnlyOrderDetailResponse(orderDetail);
                onlyOrderDetailResponses.add(onlyOrderDetailResponse);
                totalPrice += orderRequest.getPrice();
            }
            orderSave.setTotalPrice(totalPrice);
            Order orderSavePrice = orderRepository.save(orderSave);
            OrderResponse orderResponse = new OrderResponse(orderSavePrice, onlyOrderDetailResponses);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Save order success", orderResponse), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            Order order = orderRepository.findById(id).orElseThrow(null);
            if (order == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Order not found"), HttpStatus.NOT_FOUND);
            }

            if (!order.getUser().equals(user)) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Order not accept for you"), HttpStatus.BAD_REQUEST);
            }
            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderAndStatus(order, Status.ACTIVE.getValue());
            Set<OnlyOrderDetailResponse> onlyOrderDetailResponses = new HashSet<>();
            for (OrderDetail orderDetail : orderDetails) {
                OnlyOrderDetailResponse onlyOrderDetailResponse = new OnlyOrderDetailResponse(orderDetail);
                onlyOrderDetailResponses.add(onlyOrderDetailResponse);
            }
            OrderResponse orderResponse = new OrderResponse(order, onlyOrderDetailResponses);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", orderResponse), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }
}
