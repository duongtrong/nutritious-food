package com.spring.dev2chuc.nutritious_food.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Schedule extends DateAudit {
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
        this.status = Status.ACTIVE.getValue();
    }

    public Schedule() {
    }
}
