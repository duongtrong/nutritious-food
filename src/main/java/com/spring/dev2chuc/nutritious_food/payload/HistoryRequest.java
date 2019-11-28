package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryRequest {
    private Long foodId;
    private float calorie;
    private String comment;
    private int type;
}
