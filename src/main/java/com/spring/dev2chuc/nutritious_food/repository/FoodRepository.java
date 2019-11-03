package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
