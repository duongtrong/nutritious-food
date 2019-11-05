package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.RattingCombo;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.RattingComboRequest;
import com.spring.dev2chuc.nutritious_food.repository.ComboRepository;
import com.spring.dev2chuc.nutritious_food.repository.RattingComboRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ratting-combo")
public class RattingComboController {

    @Autowired
    RattingComboRepository rattingComboRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComboRepository comboRepository;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<RattingCombo> list = rattingComboRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", list), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RattingComboRequest rattingComboRequest){
        RattingCombo rattingCombo = new RattingCombo(
                rattingComboRequest.getRate(),
                rattingComboRequest.getComment(),
                rattingComboRequest.getImage()
        );

        User user = userRepository.findById(rattingComboRequest.getUserId()).orElseThrow(null);
        Combo combo = comboRepository.findById(rattingComboRequest.getComboId()).orElseThrow(null);

        rattingCombo.setUser(user);
        rattingCombo.setCombo(combo);

        RattingCombo result = rattingComboRepository.save(rattingCombo);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create success", result), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RattingComboRequest rattingComboRequest, @PathVariable Long id){
        RattingCombo rattingCombo = rattingComboRepository.findById(id).orElseThrow(null);
        if (rattingComboRequest.getRate() != null) rattingCombo.setRate(rattingComboRequest.getRate());
        if (rattingComboRequest.getComment() != null) rattingCombo.setComment(rattingComboRequest.getComment());
        if (rattingComboRequest.getImage() != null) rattingCombo.setImage(rattingComboRequest.getImage());

        User user = userRepository.findById(rattingComboRequest.getUserId()).orElseThrow(null);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        }
        rattingCombo.setUser(user);

        Combo combo = comboRepository.findById(rattingComboRequest.getComboId()).orElseThrow(null);
        if (combo == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        rattingCombo.setCombo(combo);

        RattingCombo result = rattingComboRepository.save(rattingCombo);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }
}
