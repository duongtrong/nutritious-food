package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleRequest;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import com.spring.dev2chuc.nutritious_food.service.schedule.ScheduleService;
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

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/")
    public ResponseEntity<?> getList() {
        List<Schedule> schedules = scheduleService.findAllByStatusIs(Status.ACTIVE.getValue());
        return new ResponseEntity<> (new ApiResponse<>(HttpStatus.OK.value(), "OK", schedules), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule();
        Schedule result = scheduleService.merge(schedule, scheduleRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDtail(@PathVariable("id") Long id) {
        Schedule schedule = scheduleService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (new ApiResponse<>(HttpStatus.OK.value(), "OK", schedule), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ScheduleRequest scheduleRequest, @PathVariable("id") Long id) {
        Schedule schedule = scheduleService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        Schedule result = scheduleService.update(schedule, scheduleRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Schedule schedule = scheduleService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "schedule not found"), HttpStatus.NOT_FOUND);
        }

        schedule.setStatus(Status.DEACTIVE.getValue());
        scheduleService.merge(schedule);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
