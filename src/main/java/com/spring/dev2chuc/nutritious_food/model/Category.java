package com.spring.dev2chuc.nutritious_food.model;

import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;
    private String name;
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer status;

    public Category(Long parentId, String name, String image, String description) {
        this.parentId = parentId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.status = Status.ACTIVE.getValue();
    }

    public Category() {
    }
}
