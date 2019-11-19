package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.ScheduleCombo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ComboResponse {

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
    private Set<OnlyFoodResponse> foods = new HashSet<>();
    private Set<OnlyCategoryResponse> categories = new HashSet<>();

    public ComboResponse(Combo combo) {
        this.id = combo.getId();
        this.name = combo.getName();
        this.description = combo.getDescription();
        this.image = combo.getImage();
        this.price = combo.getId();
        this.carbonhydrates = combo.getCarbonhydrates();
        this.protein = combo.getProtein();
        this.lipid = combo.getLipid();
        this.xenluloza = combo.getXenluloza();
        this.canxi = combo.getCanxi();
        this.iron = combo.getIron();
        this.zinc = combo.getZinc();
        this.vitaminA = combo.getVitaminA();
        this.vitaminB = combo.getVitaminB();
        this.vitaminC = combo.getVitaminC();
        this.vitaminD = combo.getVitaminD();
        this.vitaminE = combo.getVitaminE();
        this.calorie = combo.getCalorie();
        this.calorie = combo.getWeight();
        this.status = combo.getStatus();
        this.foods = combo.getFoods().stream().map(x -> new OnlyFoodResponse(x)).collect(Collectors.toSet());
        this.categories = combo.getCategories().stream().map(x -> new OnlyCategoryResponse(x)).collect(Collectors.toSet());
    }
}
