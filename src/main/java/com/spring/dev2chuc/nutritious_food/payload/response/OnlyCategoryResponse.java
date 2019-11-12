package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class OnlyCategoryResponse {
    private Long id;

    private Long parentId;
    private String name;
    private String image;
    private String description;
    private Integer status;

    public OnlyCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.parentId = category.getParentId();
        this.image = category.getImage();
        this.status = category.getStatus();
    }
}
