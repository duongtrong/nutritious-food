package com.spring.dev2chuc.nutritious_food.model;

import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_food", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Category> categories = new HashSet<>();

    public Food() {
    }

    public Food(String name, String description, String image, float price, float carbonhydrates, float protein, float lipid, float xenluloza, float canxi, float iron, float zinc, float vitaminA, float vitaminB, float vitaminC, float vitaminD, float vitaminE) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.carbonhydrates = carbonhydrates;
        this.protein = protein;
        this.lipid = lipid;
        this.xenluloza = xenluloza;
        this.canxi = canxi;
        this.iron = iron;
        this.zinc = zinc;
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.status = Status.ACTIVE.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCarbonhydrates() {
        return carbonhydrates;
    }

    public void setCarbonhydrates(float carbonhydrates) {
        this.carbonhydrates = carbonhydrates;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getLipid() {
        return lipid;
    }

    public void setLipid(float lipid) {
        this.lipid = lipid;
    }

    public float getXenluloza() {
        return xenluloza;
    }

    public void setXenluloza(float xenluloza) {
        this.xenluloza = xenluloza;
    }

    public float getCanxi() {
        return canxi;
    }

    public void setCanxi(float canxi) {
        this.canxi = canxi;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public float getZinc() {
        return zinc;
    }

    public void setZinc(float zinc) {
        this.zinc = zinc;
    }

    public float getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(float vitaminA) {
        this.vitaminA = vitaminA;
    }

    public float getVitaminB() {
        return vitaminB;
    }

    public void setVitaminB(float vitaminB) {
        this.vitaminB = vitaminB;
    }

    public float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public float getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(float vitaminD) {
        this.vitaminD = vitaminD;
    }

    public float getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(float vitaminE) {
        this.vitaminE = vitaminE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
