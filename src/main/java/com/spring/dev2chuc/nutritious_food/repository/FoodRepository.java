package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {

    Food findByIdAndStatus(Long id, Integer status);

    List<Food> findAllByIdIn(Collection<Long> Ids);

    Food findByStatusAndId(Integer status, Long id);

    List<Food> findAllByStatus(Integer Status);

    @Transactional
    @Modifying
    @Query(value = "alter  table foods AUTO_INCREMENT = 1 ", nativeQuery = true)
    void resetIncrement();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SET FOREIGN_KEY_CHECKS=0;")
    void disableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SET FOREIGN_KEY_CHECKS=1;")
    void enableForeignKeyCheck();

}
