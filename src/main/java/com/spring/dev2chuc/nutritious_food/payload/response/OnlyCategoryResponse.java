package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Category;

import javax.persistence.Column;

public class OnlyCategoryResponse extends Category {
    public OnlyCategoryResponse(Category category) {
        this.setId(category.getId());
        this.setName(category.getName());
        this.setDescription(category.getDescription());
        this.setParentId(category.getParentId());
        this.setImage(category.getImage());
        this.setStatus(category.getStatus());
        this.setCreatedAt(category.getCreatedAt());
    }
}
