package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.ScheduleCombo;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleComboRequest;
import com.spring.dev2chuc.nutritious_food.service.combo.ComboService;
import com.spring.dev2chuc.nutritious_food.service.schedule.ScheduleService;
import com.spring.dev2chuc.nutritious_food.service.schedulecombo.ScheduleComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schedule-combo")
public class ScheduleComboController {

    @Autowired
    ComboService comboService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleComboService scheduleComboService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        ScheduleCombo scheduleCombo = scheduleComboService.findById(id);
        if (scheduleCombo == null)
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Combo of schedule notfound"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", scheduleCombo), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> store(@Valid @RequestBody ScheduleComboRequest scheduleComboRequest) {
        ScheduleCombo scheduleCombo = new ScheduleCombo();
        Combo combo = comboService.findById(scheduleComboRequest.getComboId());
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        scheduleCombo.setCombo(combo);

        Schedule schedule = scheduleService.findById(scheduleComboRequest.getScheduleId());
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        scheduleCombo.setSchedule(schedule);

        ScheduleCombo result = scheduleComboService.merge(scheduleCombo, scheduleComboRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getAllByScheduleId(@PathVariable("id") Long id) {
        Schedule schedule = scheduleService.findById(id);
        if (schedule == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Schedule not found"), HttpStatus.NOT_FOUND);
        }
        List<ScheduleCombo> scheduleCombos = scheduleComboService.findAllBySchedule(schedule);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", scheduleCombos), HttpStatus.OK);
    }

    @GetMapping("/combo/{id}")
    public ResponseEntity<?> getAllByComboId(@PathVariable("id") Long id) {
        Combo combo = comboService.findById(id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        List<ScheduleCombo> scheduleCombos = scheduleComboService.findAllByCombo(combo);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", scheduleCombos), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ScheduleCombo scheduleCombo = scheduleComboService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (scheduleCombo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "schedule not found"), HttpStatus.NOT_FOUND);
        }

        scheduleCombo.setStatus(Status.DEACTIVE.getValue());
        scheduleComboService.merge(scheduleCombo);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
