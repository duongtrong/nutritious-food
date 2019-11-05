package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponseError;
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
        List<Schedule> schedules = scheduleRepository.findAllByStatusIs(Status.ACTIVE.getValue());
        return new ResponseEntity<> (new ApiResponse(HttpStatus.OK.value(), "OK", schedules), HttpStatus.OK);
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
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDtail(@PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (new ApiResponse(HttpStatus.OK.value(), "OK", schedule), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@Valid @RequestBody ScheduleRequest scheduleRequest, @PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        if (scheduleRequest.getName() != null) schedule.setName(scheduleRequest.getName());
        if (scheduleRequest.getDescription() != null)  schedule.setDescription(scheduleRequest.getDescription());
        if (scheduleRequest.getPrice() != 0) schedule.setPrice(scheduleRequest.getPrice());
        if (scheduleRequest.getImage() != null)  schedule.setImage(scheduleRequest.getImage());
        Schedule result = scheduleRepository.save(schedule);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "schedule not found"), HttpStatus.NOT_FOUND);
        }
        schedule.setStatus(Status.DEACTIVE.getValue());
        scheduleRepository.save(schedule);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
