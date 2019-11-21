package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class HistoryRequest {
    private Long foodId;
    private float calorie;
    private String comment;
    private int type;
}
