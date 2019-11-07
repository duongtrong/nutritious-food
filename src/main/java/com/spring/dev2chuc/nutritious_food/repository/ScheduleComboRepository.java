package com.spring.dev2chuc.nutritious_food.repository;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import com.spring.dev2chuc.nutritious_food.model.ScheduleCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleComboRepository extends JpaRepository<ScheduleCombo, Long> {
    List<ScheduleCombo> findAllByCombo(Combo combo);

    List<ScheduleCombo> findAllBySchedule(Schedule schedule);

    ScheduleCombo findByStatusAndId(Integer status, Long id);
}
