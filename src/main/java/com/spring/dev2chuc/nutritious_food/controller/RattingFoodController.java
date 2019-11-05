package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.RattingFood;
import com.spring.dev2chuc.nutritious_food.model.User;
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
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<RattingFood> rattingFoods = rattingFoodRepository.findAll();
        return new ResponseEntity<>(new ApiResponse(true, "OK", rattingFoods), HttpStatus.OK);
    }
}