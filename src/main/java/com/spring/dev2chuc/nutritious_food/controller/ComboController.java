package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.ComboRequest;
import com.spring.dev2chuc.nutritious_food.service.combo.ComboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/combo")
public class ComboController {

    @Autowired
    ComboService comboService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ComboRequest comboRequest) {
        Combo current = new Combo();
        Combo result = comboService.merge(current, comboRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create success", result), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Combo> combos = comboService.findAll();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", combos), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ComboRequest comboRequest, @PathVariable Long id) {
        Combo combo = comboService.findById(id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }

        Combo result = comboService.update(combo, comboRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Update success", result), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Combo combo = comboService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", combo), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Combo combo = comboService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        combo.setStatus(Status.DEACTIVE.getValue());
        comboService.merge(combo);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
