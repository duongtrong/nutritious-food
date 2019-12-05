package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.History;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.HistoryRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseCustom;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.HistoryDTO;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyHistoryResponse;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import com.spring.dev2chuc.nutritious_food.service.food.FoodService;
import com.spring.dev2chuc.nutritious_food.service.history.HistoryService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    FoodService foodService;

    @Autowired
    HistoryService historyService;

    @Autowired
    UserService userService;

    @Autowired
    FoodRepository foodRepository;

    @GetMapping
    public ResponseEntity<?> getList() {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            List<History> histories = historyService.findAllByUser(user);
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.OK.value(),
                    "Get your history success",
                    histories.stream().map(x -> new HistoryDTO(x, false, true)).collect(Collectors.toList())
            ), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody HistoryRequest historyRequest) {
        User user = userService.getUserAuth();
        if (user == null) {
            return new ResponseEntity<>(new ApiResponseError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        } else {
            Food food = foodService.findById(historyRequest.getFoodId());
            History history = historyService.store(historyRequest, user, food);
            return new ResponseEntity<>(new ApiResponseCustom<>(HttpStatus.CREATED.value(),
                    "Store history success",
                    new HistoryDTO(history, true, true)),
                    HttpStatus.CREATED
            );
        }

    }
}
