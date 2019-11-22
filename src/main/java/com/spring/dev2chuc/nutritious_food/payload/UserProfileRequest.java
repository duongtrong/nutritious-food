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
    private Integer bodyFat;
    private Integer exerciseIntensity;
    private Integer caloriesConsumed;
    private Integer status;
    private Set<Long> cateId;
}
