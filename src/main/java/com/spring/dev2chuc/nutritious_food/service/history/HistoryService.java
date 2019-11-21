package com.spring.dev2chuc.nutritious_food.service.history;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.History;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.HistoryRequest;

import java.util.List;

public interface HistoryService {
    List<History> findAllByUser(User user);
    History getDetailById(Long id);
    History store(HistoryRequest historyRequest, User user, Food food);
}
