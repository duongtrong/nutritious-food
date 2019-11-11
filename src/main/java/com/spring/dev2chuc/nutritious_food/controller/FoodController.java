package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponsePage;
import com.spring.dev2chuc.nutritious_food.payload.response.RESTPagination;
import com.spring.dev2chuc.nutritious_food.payload.response.SearchCriteria;
import com.spring.dev2chuc.nutritious_food.payload.response.SpecificationAll;
import com.spring.dev2chuc.nutritious_food.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "OK", foodList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id){
        Food food = foodService.findById (id);
        if (food == null) return new ResponseEntity<> (new ApiResponseError (HttpStatus.NOT_FOUND.value (), "Food not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (new ApiResponseCustom<> (HttpStatus.OK.value (), "OK", food), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody FoodRequest foodRequest) {
        Food food = new Food();
        Food current = foodService.merge(food, foodRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(), "Create new food success", current), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FoodRequest foodRequest, @PathVariable("id") Long id) {
        Food food = foodService.findById(id);
        if (food == null) {
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.NOT_FOUND.value(), "Food not found"), HttpStatus.NOT_FOUND);
        }
        Food result = foodService.update(food, foodRequest);
        return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(), "Update success", result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Food food = foodService.findByStatusAndId(Status.ACTIVE.getValue(), id);
        if (food == null) {
            return new ResponseEntity<>(new ApiResponseCustom(HttpStatus.OK.value(), "Food not found"), HttpStatus.NOT_FOUND);
        }
        food.setStatus(Status.DEACTIVE.getValue());
        foodService.merge(food);
        return new ResponseEntity<>(new ApiResponseError(HttpStatus.OK.value(), "OK"), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListPage(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "form", required = false) String form,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit){

        Specification specification = Specification.where(null);
        if (search != null && search.length() > 0) {
            specification = specification
                    .and(new SpecificationAll(new SearchCriteria("name", ":", search)))
                    .or(new SpecificationAll(new SearchCriteria("description", ":", search)));
        }

        specification = specification
                .and(new SpecificationAll(new SearchCriteria("status", ":", Status.ACTIVE.getValue())));

        Page<Food> foodPage = foodService.foodsWithPaginate(specification, page, limit);
        return new ResponseEntity<>(new ApiResponsePage<>(
                HttpStatus.OK.value(), "OK", foodPage,
                new RESTPagination(page, limit, foodPage.getTotalPages(), foodPage.getTotalElements())), HttpStatus.OK);
    }
}
