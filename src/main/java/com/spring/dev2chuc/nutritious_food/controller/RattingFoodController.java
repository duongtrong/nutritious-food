package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.*;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.RattingFoodRequest;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import com.spring.dev2chuc.nutritious_food.repository.RattingFoodRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ratting-food")
public class RattingFoodController {

    @Autowired
    RattingFoodRepository rattingFoodRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RattingFoodRequest rattingFoodRequest) {
        RattingFood rattingFood = new RattingFood(rattingFoodRequest.getRate(),
                rattingFoodRequest.getComment(),
                rattingFoodRequest.getImage());
        User user = userRepository.findById(rattingFoodRequest.getUserId()).orElseThrow(null);
        Food food = foodRepository.findById(rattingFoodRequest.getFoodId()).orElseThrow(null);
        rattingFood.setUser(user);
        rattingFood.setFood(food);
        RattingFood result = rattingFoodRepository.save(rattingFood);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "OK", result), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<RattingFood> rattingFoods = rattingFoodRepository.findAll();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", rattingFoods), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RattingFoodRequest rattingFoodRequest, @PathVariable Long id){
        RattingFood rattingFood = rattingFoodRepository.findById(id).orElseThrow(null);
        if (rattingFoodRequest.getRate() != null) rattingFood.setRate(rattingFoodRequest.getRate());
        if (rattingFoodRequest.getComment() != null) rattingFood.setComment(rattingFoodRequest.getComment());
        if (rattingFoodRequest.getImage() != null) rattingFood.setImage(rattingFoodRequest.getImage());

        User user = userRepository.findById(rattingFoodRequest.getUserId()).orElseThrow(null);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        }
        rattingFood.setUser(user);

        Food food = foodRepository.findById(rattingFoodRequest.getFoodId()).orElseThrow(null);
        if (food == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Combo not found"), HttpStatus.NOT_FOUND);
        }
        rattingFood.setFood(food);

        RattingFood result = rattingFoodRepository.save(rattingFood);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }
}