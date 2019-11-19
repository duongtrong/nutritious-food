package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long>, JpaSpecificationExecutor<Combo> {
    Combo findByStatusAndId(Integer status, Long id);

    List<Combo> findAllByStatus(int Status);

    List<Combo> findAllByStatusIs(Integer status);

    List<Combo> findAllByStatusAndIdIn(Integer status, List<Long> ids);
}
