package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody FoodRequest foodRequest) {
        Food food = new Food(
                foodRequest.getName(),
                foodRequest.getDescription(),
                foodRequest.getImage(),
                foodRequest.getPrice(),
                foodRequest.getCarbonhydrates(),
                foodRequest.getProtein(),
                foodRequest.getLipid(),
                foodRequest.getXenluloza(),
                foodRequest.getCanxi(),
                foodRequest.getIron(),
                foodRequest.getZinc(),
                foodRequest.getVitaminA(),
                foodRequest.getVitaminB(),
                foodRequest.getVitaminC(),
                foodRequest.getVitaminD(),
                foodRequest.getVitaminE()
                );
        Food result = foodRepository.save(food);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }
}
