package com.spring.dev2chuc.nutritious_food.service.combo;

import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.payload.ComboRequest;

import java.util.List;

public interface ComboService {

    Combo findById(Long id);

    List<Combo> findAll();

    Combo update(Combo combo, ComboRequest comboRequest);

    Combo merge(Combo combo);

    Combo merge(Combo combo, ComboRequest comboRequest);

    Combo findByStatusAndId(Integer status, Long id);
}
