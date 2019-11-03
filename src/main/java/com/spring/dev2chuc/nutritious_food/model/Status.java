package com.spring.dev2chuc.nutritious_food.model;

public enum Status {
    ACTIVE(1),
    DEACTIVE(0);

    private Integer value;

    Status(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Status fromValue(Integer value) {
        Status status = DEACTIVE;
        switch (value) {
            case 0:
                status = ACTIVE;
                break;
            case 9:
                status = DEACTIVE;
                break;
            default:
                break;
        }
        return status;
    }
}
