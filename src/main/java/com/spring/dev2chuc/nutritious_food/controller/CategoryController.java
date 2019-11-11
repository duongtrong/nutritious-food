package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.helper.CategoryHelper;
import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.CategoryRequest;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category current = new Category();
        Category result = categoryService.merge(current, categoryRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(), "Create success", result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id) {
        Category result = categoryService.findByIdAndStatus(id, Status.ACTIVE.getValue());
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> store(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        Category category = categoryService.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Category not found"), HttpStatus.NOT_FOUND);
        }
        Category result = categoryService.update(category, categoryRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Update success", result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Category result = categoryService.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (result == null) {
            return new ResponseEntity<>(new ApiResponseCustom(HttpStatus.OK.value(), "Category not found"), HttpStatus.NOT_FOUND);
        }
        result.setStatus(Status.DEACTIVE.getValue());
        categoryService.merge(result);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<Category> result = categoryService.findAllByStatusIs(Status.ACTIVE.getValue());
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<?> getByParentId(@PathVariable("id") Long id) {
        List<Category> result = categoryService.findByCategoriesByParentIdAndStatus(id, Status.ACTIVE.getValue());
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> latest() {
        List<Category> categoryList = categoryService.findAll();
        List<Category> categoryResult = new ArrayList<Category>();
        Long parentId = Long.valueOf("0");
        List<Category> result = CategoryHelper.recusiveCategory(categoryList, parentId, "", categoryResult);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", result), HttpStatus.OK);
    }
}
