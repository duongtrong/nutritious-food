package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.RattingScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.RattingScheduleRepository;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ratting-schedule")
public class RattingScheduleController {

    @Autowired
    RattingScheduleRepository rattingScheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<RattingSchedule> list = rattingScheduleRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", list), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RattingScheduleRequest rattingScheduleRequest){
        RattingSchedule rattingSchedule = new RattingSchedule(
                rattingScheduleRequest.getRate(),
                rattingScheduleRequest.getComment(),
                rattingScheduleRequest.getImage()
        );

        User user = userRepository.findById(rattingScheduleRequest.getUserId()).orElseThrow(null);
        Schedule schedule = scheduleRepository.findById(rattingScheduleRequest.getScheduleId()).orElseThrow(null);

        rattingSchedule.setUser(user);
        rattingSchedule.setSchedule(schedule);

        RattingSchedule result = rattingScheduleRepository.save(rattingSchedule);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create success", result), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RattingScheduleRequest rattingScheduleRequest, @PathVariable Long id){
        RattingSchedule rattingSchedule = rattingScheduleRepository.findById(id).orElseThrow(null);
        if (rattingScheduleRequest.getRate() != null) rattingSchedule.setRate(rattingScheduleRequest.getRate());
        if (rattingScheduleRequest.getComment() != null) rattingSchedule.setComment(rattingScheduleRequest.getComment());
        if (rattingScheduleRequest.getImage() != null) rattingSchedule.setImage(rattingScheduleRequest.getImage());

        User user = userRepository.findById(rattingScheduleRequest.getUserId()).orElseThrow(null);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        }
        rattingSchedule.setUser(user);

        Schedule schedule = scheduleRepository.findById(rattingScheduleRequest.getScheduleId()).orElseThrow(null);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        rattingSchedule.setSchedule(schedule);

        RattingSchedule result = rattingScheduleRepository.save(rattingSchedule);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }
}
