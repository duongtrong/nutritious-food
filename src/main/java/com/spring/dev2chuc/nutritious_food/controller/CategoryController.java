package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.CategoryRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getParentId(), categoryRequest.getName(), categoryRequest.getImage(), categoryRequest.getDescription());
        Category result = categoryRepository.save(category);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED.value(), "Create success", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Category result = categoryRepository.findById(id).orElseThrow(null);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(null);
        if (categoryRequest.getName() != null) category.setName(categoryRequest.getName());
        if (categoryRequest.getImage() != null) category.setImage(categoryRequest.getImage());
        if (categoryRequest.getDescription() != null) category.setDescription(categoryRequest.getDescription());
        if (categoryRequest.getParentId() != null) category.setParentId(categoryRequest.getParentId());
        Category result = categoryRepository.save(category);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Update success", result), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> listAll() {
        List<Category> result = categoryRepository.findAll();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<?> getByParentId(@PathVariable("id") Long id) {
        List<Category> result = categoryRepository.queryCategoriesByParentId(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

//    @GetMapping("/latest")
//    public ResponseEntity<?> latest() {
//        Category result = categoryRepository.findAll(Sort("created_at", "")).orElseThrow(null);
//        return new ResponseEntity(new ApiResponse(true, "ok", result), HttpStatus.OK);
//    }
}
