package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.History;
import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> queryAllByUserAndStatus(User user, Integer status);

    List<History> findAllByUser(User user);
}
