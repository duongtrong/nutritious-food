package com.spring.dev2chuc.nutritious_food.model;

import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "user_profile")
public class UserProfile extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer status;

    public UserProfile(User user, Integer height, Integer weight, Integer age) {
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.status = Status.ACTIVE.getValue();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}