package com.spring.dev2chuc.nutritious_food.model;

public enum Gender {

    MALE(1),
    FEMALE(2);

    private Integer value;

    Gender(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
