package com.spring.dev2chuc.nutritious_food.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private float price;
    private String image;
    private Integer status;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private Set<ScheduleCombo> scheduleCombos;

    public Schedule(String name, String description, float price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Schedule() {
    }

    public Set<ScheduleCombo> getScheduleCombos() {
        return scheduleCombos;
    }

    public void setScheduleCombos(Set<ScheduleCombo> scheduleCombos) {
        this.scheduleCombos = scheduleCombos;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
