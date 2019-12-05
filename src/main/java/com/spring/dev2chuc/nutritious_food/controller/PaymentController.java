package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDTO;
import com.spring.dev2chuc.nutritious_food.service.vnpay.VnPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/return")
    public ResponseEntity<?> catchReturnVnPay(HttpServletRequest request) throws UnsupportedEncodingException {
        String errorCode = vnPayService.catchDataReturn(request);
        if ("00".equals(errorCode)) {
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Giao dịch thành công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("payment.error.dr." + errorCode, null, null)), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/ipn")
    public ResponseEntity<?> catchIPNVnPay(HttpServletRequest request) throws UnsupportedEncodingException {
        String errorCode = vnPayService.catchDataIPN(request);
        if ("00".equals(errorCode)) {
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Giao dịch thành công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.BAD_REQUEST.value(), messageSource.getMessage("payment.error.dr." + errorCode, null, null)), HttpStatus.BAD_REQUEST);
        }
    }


}
