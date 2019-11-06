package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/")
    public ResponseEntity<?> getList() {
        List<Schedule> schedules = scheduleRepository.findAll ();
        return new ResponseEntity<> (new ApiResponse(true, "OK", schedules), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule(
                scheduleRequest.getName(),
                scheduleRequest.getDescription(),
                scheduleRequest.getPrice(),
                scheduleRequest.getImage()
        );
        Schedule result = scheduleRepository.save(schedule);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(null);
        return new ResponseEntity<> (new ApiResponse(true, "OK", schedule), HttpStatus.OK);
    }
}
