package com.spring.dev2chuc.nutritious_food.payload;

import com.spring.dev2chuc.nutritious_food.model.ExerciseIntensity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserProfileRequest {
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer bodyFat;
    private Float exerciseIntensity;
    private Integer caloriesConsumed;
    private Integer status;
    private Set<Long> categoryIds;
}
