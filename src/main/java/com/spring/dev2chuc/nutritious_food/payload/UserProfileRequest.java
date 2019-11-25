package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserProfileRequest {
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer gender;
    private Integer bodyFat;
    private Double exerciseIntensity;
    private Integer caloriesConsumed;
    private Integer status;
    private Set<Long> categoryIds;

    public Integer getCaloriesConsumed(Integer mathCalories) {
        return mathCalories;
    }
}
