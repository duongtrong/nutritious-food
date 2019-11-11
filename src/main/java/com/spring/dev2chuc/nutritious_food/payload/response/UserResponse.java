package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.User;

public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Integer status;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }

}
