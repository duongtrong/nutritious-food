package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Device;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.DeviceRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.DeviceDTO;
import com.spring.dev2chuc.nutritious_food.service.device.DeviceService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    UserService userService;

    @Autowired
    DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DeviceRequest deviceRequest) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            Device device = deviceService.store(user, deviceRequest);
            if (device == null)
                return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.BAD_REQUEST.value(), "Item not match"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(), "Create device success", new DeviceDTO(device)), HttpStatus.CREATED);
        }
    }
}
