package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.History;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class HistoryDTO {
    private Long id;
    private float calorie;
    private String comment;
    private int type;
    private int status;
    private Instant createdAt;
    private OnlyUserResponse user;
    private FoodDTO food;


    public HistoryDTO(History history) {
        this.id = history.getId();
        this.calorie = history.getCalorie();
        this.comment = history.getComment();
        this.type = history.getType();
        this.status = history.getStatus();
        this.user = new OnlyUserResponse(history.getUser());
        this.food = new FoodDTO(history.getFood(), false, false);
        this.createdAt = history.getCreatedAt();
    }
}