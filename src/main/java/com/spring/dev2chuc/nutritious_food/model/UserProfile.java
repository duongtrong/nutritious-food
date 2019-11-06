package com.spring.dev2chuc.nutritious_food.model;

import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_user_profile",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public UserProfile(User user, Integer height, Integer weight, Integer age) {
        this.user = user;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.status = Status.ACTIVE.getValue();
    }

    public UserProfile() {
    }
}