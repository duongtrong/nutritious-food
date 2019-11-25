package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.OrderDetail;
import com.spring.dev2chuc.nutritious_food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderAndStatus(Order order, Integer status);
}
