package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            List<Order> orderList = orderRepository.findAllByUser(user);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", orderList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody List<OrderRequest> orderRequests) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
            }
            Order order = new Order(user, (float) 0);
            Order orderSave = orderRepository.save(order);
            float totalPrice = 0;
            for (OrderRequest orderRequest : orderRequests) {
                System.out.println(orderRequest.getFoodId());
                Food food = foodRepository.findById(orderRequest.getFoodId()).orElseThrow(null);
                Combo combo = comboRepository.findById(orderRequest.getComboId()).orElseThrow(null);
                Schedule schedule = scheduleRepository.findById(orderRequest.getScheduleId()).orElseThrow(null);
                System.out.println(combo.getName());
                OrderDetail orderDetailCurrent = new OrderDetail(
                        orderSave,
                        food,
                        combo,
                        schedule,
                        orderRequest.getQuantity(),
                        orderRequest.getPrice()
                        );
                orderDetailRepository.save(orderDetailCurrent);
                totalPrice += orderRequest.getPrice();
            }
            orderSave.setTotalPrice(totalPrice);
            Order orderSavePrice = orderRepository.save(orderSave);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "User not found", orderSavePrice), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
    }
}
