package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.*;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.repository.*;
import com.spring.dev2chuc.nutritious_food.service.order.OrderService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
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
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getListByUser() {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            List<OrderResponse> orderList = orderService.getAllByUser(user);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", orderList), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody List<OrderRequest> orderRequests) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            OrderResponse orderResponse = orderService.saveOrderByUser(user, orderRequests);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Save order success", orderResponse), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("id") Long id) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            OrderResponse orderResponse = orderService.getById(id);
            if (orderResponse == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Order not found"), HttpStatus.NOT_FOUND);
            }
            if (orderResponse.getUserId() != user.getId()) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Order not accept for you"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Save order success", orderResponse), HttpStatus.CREATED);
        }
    }
}
