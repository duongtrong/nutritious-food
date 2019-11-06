package com.spring.dev2chuc.nutritious_food.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PasswordChange implements Serializable {
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
