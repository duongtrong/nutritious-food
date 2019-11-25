package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Address;
import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByAddressIn(List<Address> addresses);
}
