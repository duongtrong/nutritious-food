package com.spring.dev2chuc.nutritious_food.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ScheduleCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "combo_id", nullable = false)
    private Combo combo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private Integer day;
    private Integer type;
    private Integer status;

    public ScheduleCombo(Combo combo, Schedule schedule, Integer day, Integer type) {
        this.combo = combo;
        this.schedule = schedule;
        this.day = day;
        this.type = type;
        this.status = Status.ACTIVE.getValue();
    }

    public ScheduleCombo(Integer day, Integer type) {
        this.day = day;
        this.type = type;
    }

    public ScheduleCombo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
