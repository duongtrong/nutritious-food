package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
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
    FoodRepository foodRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Food> foodList = foodRepository.findAll ();
        return new ResponseEntity<> (new ApiResponse (true, "OK", foodList), HttpStatus.OK);
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<?> create(@Valid @RequestBody FoodRequest foodRequest, @PathVariable("id") List<Long> id) {
        Food food = new Food (
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
        List<Category> categories = categoryRepository.findAllByIdIn (id);
        Set<Category> categorySet = new HashSet<> (categories);
        food.setCategories (categorySet);
        Food current = foodRepository.save (food);
        return new ResponseEntity(new ApiResponse (true, "Create new food success", current), HttpStatus.CREATED);
    }

    @PutMapping("{id}/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FoodRequest foodRequest, @PathVariable("id") Long id, @PathVariable("id") List<Long> idn){
        Food food = foodRepository.findById (id).orElseThrow (null);
        if (foodRequest.getName() != null) food.setName(foodRequest.getName());
        if (foodRequest.getDescription() != null)food.setDescription(foodRequest.getDescription());
        if (foodRequest.getImage() != null)food.setImage(foodRequest.getImage());
        if (foodRequest.getPrice () != 0.0f) food.setPrice (foodRequest.getPrice ());
        if (foodRequest.getCarbonhydrates () != 0.0f) food.setCarbonhydrates (foodRequest.getCarbonhydrates ());
        if (foodRequest.getProtein () != 0.0f) food.setProtein (foodRequest.getProtein ());
        if (foodRequest.getLipid () != 0.0f) food.setLipid (foodRequest.getLipid ());
        if (foodRequest.getXenluloza () != 0.0f) food.setXenluloza (foodRequest.getXenluloza ());
        if (foodRequest.getCanxi () != 0.0f) food.setCanxi (foodRequest.getCanxi ());
        if (foodRequest.getIron () != 0.0f) food.setIron (foodRequest.getIron ());
        if (foodRequest.getZinc () != 0.0f) food.setZinc (foodRequest.getZinc ());
        if (foodRequest.getVitaminA () != 0.0f) food.setVitaminA (foodRequest.getVitaminA ());
        if (foodRequest.getVitaminB () != 0.0f) food.setVitaminB (foodRequest.getVitaminB ());
        if (foodRequest.getVitaminC () != 0.0f) food.setVitaminC (foodRequest.getVitaminC ());
        if (foodRequest.getVitaminD () != 0.0f) food.setVitaminD (foodRequest.getVitaminD ());
        if (foodRequest.getVitaminE () != 0.0f) food.setVitaminE (foodRequest.getVitaminE ());

        List<Category> categories = categoryRepository.findAllByIdIn(idn);
        Set<Category> categorySet = new HashSet<> (categories);
        food.setCategories (categorySet);
        Food result = foodRepository.save (food);

        return new ResponseEntity(new ApiResponse (true, "Update success", result), HttpStatus.OK);
    }
}
