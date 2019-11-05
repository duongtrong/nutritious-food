package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.ComboRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import com.spring.dev2chuc.nutritious_food.repository.ComboRepository;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/combo")
public class ComboController {

    @Autowired
    ComboRepository comboRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    FoodRepository foodRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ComboRequest comboRequest) {
//        logger.info("Cat id: {} - foodId: {}", catId, foodId);
        Combo combo = new Combo(
                comboRequest.getName(),
                comboRequest.getDescription(),
                comboRequest.getImage(),
                comboRequest.getPrice(),
                comboRequest.getCarbonhydrates(),
                comboRequest.getProtein(),
                comboRequest.getLipid(),
                comboRequest.getXenluloza(),
                comboRequest.getCanxi(),
                comboRequest.getIron(),
                comboRequest.getZinc(),
                comboRequest.getVitaminA(),
                comboRequest.getVitaminB(),
                comboRequest.getVitaminC(),
                comboRequest.getVitaminD(),
                comboRequest.getVitaminE()
        );

        List<Category> categories = categoryRepository.findAllByIdIn(comboRequest.getCategoryIds());
        Set<Category> categorySet = new HashSet<>(categories);
        combo.setCategories(categorySet);

        List<Food> foodList = foodRepository.findAllByIdIn(comboRequest.getFoodIds());
        Set<Food> foodSet = new HashSet<>(foodList);
        combo.setFoodSet(foodSet);

        Combo result = comboRepository.save(combo);
        return new ResponseEntity<>(new ApiResponse(true, "Create success", result), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Combo> combos = comboRepository.findAll();
        return new ResponseEntity<>(new ApiResponse(true, "OK", combos), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@Valid @RequestBody ComboRequest comboRequest,
                                    @PathVariable Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(null);
        if (comboRequest.getName() != null) combo.setName(comboRequest.getName());
        if (comboRequest.getDescription() != null) combo.setDescription(comboRequest.getDescription());
        if (comboRequest.getImage() != null) combo.setImage(comboRequest.getImage());
        if (comboRequest.getPrice() != 0.0f) combo.setPrice(comboRequest.getPrice());
        if (comboRequest.getCarbonhydrates() != 0.0f) combo.setCarbonhydrates(comboRequest.getCarbonhydrates());
        if (comboRequest.getProtein() != 0.0f) combo.setProtein(comboRequest.getProtein());
        if (comboRequest.getLipid() != 0.0f) combo.setLipid(comboRequest.getLipid());
        if (comboRequest.getXenluloza() != 0.0f) combo.setXenluloza(comboRequest.getXenluloza());
        if (comboRequest.getCanxi() != 0.0f) combo.setCanxi(comboRequest.getCanxi());
        if (comboRequest.getIron() != 0.0f) combo.setIron(comboRequest.getIron());
        if (comboRequest.getZinc() != 0.0f) combo.setZinc(comboRequest.getZinc());
        if (comboRequest.getVitaminA() != 0.0f) combo.setVitaminA(comboRequest.getVitaminA());
        if (comboRequest.getVitaminB() != 0.0f) combo.setVitaminB(comboRequest.getVitaminB());
        if (comboRequest.getVitaminC() != 0.0f) combo.setVitaminC(comboRequest.getVitaminC());
        if (comboRequest.getVitaminD() != 0.0f) combo.setVitaminD(comboRequest.getVitaminD());
        if (comboRequest.getVitaminE() != 0.0f) combo.setVitaminE(comboRequest.getVitaminE());
        if (comboRequest.getFoodIds().size() > 0) {
            List<Category> categories = categoryRepository.findAllByIdIn(comboRequest.getFoodIds());
            Set<Category> categorySet = new HashSet<>(categories);
            combo.setCategories(categorySet);
        }
        if (comboRequest.getCategoryIds().size() > 0) {
            List<Food> foodList = foodRepository.findAllByIdIn(comboRequest.getCategoryIds());
            Set<Food> foodSet = new HashSet<>(foodList);
            combo.setFoodSet(foodSet);
        }
        Combo result = comboRepository.save(combo);

        return new ResponseEntity<>(new ApiResponse(true, "Update success", result), HttpStatus.OK);
    }
}
