package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDTO;
import com.spring.dev2chuc.nutritious_food.service.vnpay.VnPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    VnPayService vnPayService;

    @GetMapping(value = "/return")
    public ResponseEntity<?> catchReturnVnPay(HttpServletRequest request) throws UnsupportedEncodingException {
        vnPayService.catchDataReturn(request);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
