package com.spring.dev2chuc.nutritious_food.model;

import com.spring.dev2chuc.nutritious_food.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private float totalPrice;
    private int type;
    private int status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    public Order(User user, float totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = Status.ACTIVE.getValue();
    }

    public Order() {
    }
}
