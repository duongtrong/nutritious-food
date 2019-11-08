package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import com.spring.dev2chuc.nutritious_food.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "OK", foodList), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody FoodRequest foodRequest) {
        Food food = new Food();
        Food current = foodService.merge(food, foodRequest);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), "Create new food success", current), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FoodRequest foodRequest, @PathVariable("id") Long id) {
        Food food = foodService.findById(id);
        if (food == null) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Food not found"), HttpStatus.NOT_FOUND);
        }

        Food result = foodService.merge(food);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Update success", result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Food food = foodService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (food == null) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Food not found"), HttpStatus.NOT_FOUND);
        }
        food.setStatus(Status.DEACTIVE.getValue());
        foodService.merge(food);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }
}
