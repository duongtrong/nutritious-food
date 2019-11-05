package com.spring.dev2chuc.nutritious_food.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class FoodRequest {

    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private float price;
    private float carbonhydrates;
    private float protein;
    private float lipid;
    private float xenluloza;
    private float canxi;
    private float iron;
    private float zinc;
    private float vitaminA;
    private float vitaminB;
    private float vitaminC;
    private float vitaminD;
    private float vitaminE;
}
