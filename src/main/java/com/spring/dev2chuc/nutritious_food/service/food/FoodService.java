package com.spring.dev2chuc.nutritious_food.service.food;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;

import java.util.Collection;
import java.util.List;

public interface FoodService {

    Food merge(Food food);

    List<Food> findAll();

    Food findById(Long id);

    Food update(Food food, FoodRequest foodRequest);

    Food merge(Food food, FoodRequest foodRequest);

    Food findByStatusAndId(Integer status, Long id);

    List<Food> findAllByIdIn(Collection<Long> ids);
}
