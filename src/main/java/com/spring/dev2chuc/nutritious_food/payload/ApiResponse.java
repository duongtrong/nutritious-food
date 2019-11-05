package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object object;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, Object object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }
}
