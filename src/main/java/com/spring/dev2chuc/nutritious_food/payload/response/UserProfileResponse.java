package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.UserProfile;

import java.util.HashSet;
import java.util.Set;

public class UserProfileResponse {
    private Long id;
    private Long user_id;
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer status;
    private Set<Category> categories;


    public UserProfileResponse(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.user_id = userProfile.getUser().getId();
        this.height = userProfile.getHeight();
        this.weight = userProfile.getWeight();
        this.age = userProfile.getAge();
        this.status = userProfile.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
