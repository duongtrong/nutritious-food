package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer status;
}
