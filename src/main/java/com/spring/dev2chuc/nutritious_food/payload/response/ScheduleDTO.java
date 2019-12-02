package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private String name;
    private String description;
    private float price;
    private String image;
    private Integer status;
    private List<ScheduleComboDTO> scheduleCombo;
    private List<CategoryDTO> categories;

    public ScheduleDTO(Schedule schedule, boolean hasScheduleCombo, boolean hasCategory) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.description = schedule.getDescription();
        this.price = schedule.getPrice();
        this.image = schedule.getImage();
        this.status = schedule.getStatus();
        if (hasScheduleCombo)
            this.scheduleCombo = schedule
                    .getScheduleCombos()
                    .stream()
                    .map(x -> new ScheduleComboDTO(x ,false, false))
                    .collect(Collectors.toList());
        if (hasCategory)
            this.categories = schedule
                    .getCategories()
                    .stream()
                    .map(x -> new CategoryDTO(x, false, false))
                    .collect(Collectors.toList());
    }
}
