package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDTO;
import com.spring.dev2chuc.nutritious_food.service.order.OrderService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import com.spring.dev2chuc.nutritious_food.service.vnpay.VnPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    VnPayService vnPayService;

    @GetMapping
    public ResponseEntity<?> getListByUser() {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            List<OrderDTO> orderList = orderService.getAllByUser(user);
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", orderList), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest, HttpServletRequest req) throws UnsupportedEncodingException {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            Order order = orderService.saveOrderByUser(orderRequest);

            if (order == null)
                return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.BAD_REQUEST.value(), "Item not match"), HttpStatus.BAD_REQUEST);
            String urlVnPay = "";
            if (order.getType() == 2) {
                urlVnPay = vnPayService.generateURLPayment(req, order);
            }
            OrderDTO orderDTO = new OrderDTO(order, true);
            orderDTO.setUrlPayment(urlVnPay);
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(), "Save order success", orderDTO), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("id") Long id) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            OrderDTO orderResponse = orderService.getById(id);
            if (orderResponse == null) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Order not found"), HttpStatus.NOT_FOUND);
            }
            if (orderResponse.getUserId() != user.getId()) {
                return new ResponseEntity<>(new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Order not accept for you"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", orderResponse), HttpStatus.OK);
        }
    }
}
