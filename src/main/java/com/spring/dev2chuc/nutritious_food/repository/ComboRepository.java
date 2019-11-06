package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByIdAndStatus(Long id, int Status);
    List<Combo> findAllByStatus(int Status);
}
