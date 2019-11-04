package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.ScheduleCombo;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ScheduleComboRequest;
import com.spring.dev2chuc.nutritious_food.repository.ComboRepository;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleComboRepository;
import com.spring.dev2chuc.nutritious_food.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/schedule-combo")
public class ScheduleComboController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ComboRepository comboRepository;

    @Autowired
    ScheduleComboRepository scheduleComboRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        ScheduleCombo scheduleCombo = scheduleComboRepository.findById(id).orElseThrow(null);
        if (scheduleCombo == null) return new ResponseEntity<> (new ApiResponse(false, "Combo of schedule notfound"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (new ApiResponse(true, "OK", scheduleCombo), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> store(@Valid @RequestBody ScheduleComboRequest scheduleComboRequest) {
        Combo combo = comboRepository.findById(scheduleComboRequest.getComboId()).orElseThrow(null);
        Schedule schedule = scheduleRepository.findById(scheduleComboRequest.getScheduleId()).orElseThrow(null);
        ScheduleCombo scheduleCombo = new ScheduleCombo(scheduleComboRequest.getDay(), scheduleComboRequest.getType());
        scheduleCombo.setCombo(combo);
        scheduleCombo.setSchedule(schedule);
        ScheduleCombo result = scheduleComboRepository.save(scheduleCombo);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getAllByScheduleId(@PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(null);
        List<ScheduleCombo> scheduleCombos = scheduleComboRepository.findAllBySchedule(schedule);
        return new ResponseEntity<> (new ApiResponse(true, "OK", scheduleCombos), HttpStatus.OK);
    }

    @GetMapping("/combo/{id}")
    public ResponseEntity<?> getAllByComboId(@PathVariable("id") Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(null);
        List<ScheduleCombo> scheduleCombos = scheduleComboRepository.findAllByCombo(combo);
        return new ResponseEntity<> (new ApiResponse(true, "OK", scheduleCombos), HttpStatus.OK);
    }
}
