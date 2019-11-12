package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlyFoodResponse {
    private Long id;
    private String name;
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
    private float calorie;
    private float weight;
    private int status;

    public OnlyFoodResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.description = food.getDescription();
        this.image = food.getImage();
        this.price = food.getId();
        this.carbonhydrates = food.getCarbonhydrates();
        this.protein = food.getProtein();
        this.lipid = food.getLipid();
        this.xenluloza = food.getXenluloza();
        this.canxi = food.getCanxi();
        this.iron = food.getIron();
        this.zinc = food.getZinc();
        this.vitaminA = food.getVitaminA();
        this.vitaminB = food.getVitaminB();
        this.vitaminC = food.getVitaminC();
        this.vitaminD = food.getVitaminD();
        this.vitaminE = food.getVitaminE();
        this.calorie = food.getCalorie();
        this.weight = food.getWeight();
        this.status = food.getStatus();
    }
}
