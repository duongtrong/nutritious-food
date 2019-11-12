package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlyScheduleResponse {
    private Long id;
    private String name;
    private String description;
    private float price;
    private String image;
    private Integer status;

    public OnlyScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.description = schedule.getDescription();
        this.price = schedule.getPrice();
        this.image = schedule.getImage();
        this.status = schedule.getStatus();
    }
}
