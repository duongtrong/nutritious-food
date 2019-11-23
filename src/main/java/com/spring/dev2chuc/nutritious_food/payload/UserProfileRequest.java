package com.spring.dev2chuc.nutritious_food.payload;

import com.spring.dev2chuc.nutritious_food.model.ExerciseIntensity;
import com.spring.dev2chuc.nutritious_food.model.Gender;
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
}
