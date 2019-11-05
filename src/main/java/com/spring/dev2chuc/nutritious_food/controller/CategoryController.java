package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.helper.CategoryHelper;
import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ApiResponse;
import com.spring.dev2chuc.nutritious_food.payload.CategoryRequest;
import com.spring.dev2chuc.nutritious_food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Category result = categoryRepository.findById(id).orElseThrow(null);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        Category category = categoryRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Category not found"), HttpStatus.NOT_FOUND);
        }
        if (categoryRequest.getName() != null) category.setName(categoryRequest.getName());
        if (categoryRequest.getImage() != null) category.setImage(categoryRequest.getImage());
        if (categoryRequest.getDescription() != null) category.setDescription(categoryRequest.getDescription());
        if (categoryRequest.getParentId() != null) category.setParentId(categoryRequest.getParentId());
        Category result = categoryRepository.save(category);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Category result = categoryRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (result == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Category not found"), HttpStatus.NOT_FOUND);
        }
        result.setStatus(Status.DEACTIVE.getValue());
        categoryRepository.save(result);
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> listAll() {
        List<Category> result = categoryRepository.findAllByStatusIs(Status.ACTIVE.getValue());
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.OK);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<?> getByParentId(@PathVariable("id") Long id) {
        List<Category> result = categoryRepository.queryCategoriesByParentIdAndStatus(id, Status.ACTIVE.getValue());
        return new ResponseEntity<>(new ApiResponse(true, "ok", result), HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> latest() {
        List<Category> categoryList = categoryRepository.findAll();
//        Collections.reverse(categoryList);
//        return new ResponseEntity(new ApiResponse(true, "ok", categoryList), HttpStatus.OK);
        List<Category> categoryResult = new ArrayList<Category>();
        Long parentId = Long.valueOf("0");
        List<Category> result = CategoryHelper.recusiveCategory(categoryList, parentId, "", categoryResult);
        return new ResponseEntity(new ApiResponse(true, "ok", result), HttpStatus.OK);
    }
}
