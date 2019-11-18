package com.spring.dev2chuc.nutritious_food.service.combo;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.ComboRequest;
import com.spring.dev2chuc.nutritious_food.repository.ComboRepository;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import com.spring.dev2chuc.nutritious_food.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComboServiceImpl implements ComboService{

    @Autowired
    ComboRepository comboRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    FoodService foodService;

    @Override
    public Combo findById(Long id) {
        if (CollectionUtils.isEmpty(Collections.singleton(id))) {
            throw new NullPointerException("Null pointer exception");
        }
        return comboRepository.findByStatusAndId(Status.ACTIVE.getValue(), id);
    }

    @Override
    public List<Combo> findAll() {
        return comboRepository.findAll();
    }

    @Override
    public Combo merge(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Combo combo, ComboRequest comboRequest) {
        if (comboRequest.getName() != null) combo.setName(comboRequest.getName());
        if (comboRequest.getDescription() != null) combo.setDescription(comboRequest.getDescription());
        if (comboRequest.getImage() != null) combo.setImage(comboRequest.getImage());
        if (comboRequest.getPrice() != 0.0f) combo.setPrice(comboRequest.getPrice());
        if (comboRequest.getCarbonhydrates() != 0.0f) combo.setCarbonhydrates(comboRequest.getCarbonhydrates());
        if (comboRequest.getProtein() != 0.0f) combo.setProtein(comboRequest.getProtein());
        if (comboRequest.getLipid() != 0.0f) combo.setLipid(comboRequest.getLipid());
        if (comboRequest.getXenluloza() != 0.0f) combo.setXenluloza(comboRequest.getXenluloza());
        if (comboRequest.getCanxi() != 0.0f) combo.setCanxi(comboRequest.getCanxi());
        if (comboRequest.getIron() != 0.0f) combo.setIron(comboRequest.getIron());
        if (comboRequest.getZinc() != 0.0f) combo.setZinc(comboRequest.getZinc());
        if (comboRequest.getVitaminA() != 0.0f) combo.setVitaminA(comboRequest.getVitaminA());
        if (comboRequest.getVitaminB() != 0.0f) combo.setVitaminB(comboRequest.getVitaminB());
        if (comboRequest.getVitaminC() != 0.0f) combo.setVitaminC(comboRequest.getVitaminC());
        if (comboRequest.getVitaminD() != 0.0f) combo.setVitaminD(comboRequest.getVitaminD());
        if (comboRequest.getVitaminE() != 0.0f) combo.setVitaminE(comboRequest.getVitaminE());
        if (comboRequest.getCalorie() != 0.0f) combo.setCalorie(comboRequest.getCalorie());
        if (comboRequest.getWeight() != 0.0f) combo.setWeight(comboRequest.getWeight());
        if (comboRequest.getFoodIds().size() > 0) {
            List<Category> categories = categoryService.findAllByIdIn(comboRequest.getFoodIds());
            Set<Category> categorySet = new HashSet<>(categories);
            combo.setCategories(categorySet);
        }

        if (comboRequest.getCategoryIds().size() > 0) {
            List<Food> foodList = foodService.findAllByIdIn(comboRequest.getCategoryIds());
            Set<Food> foodSet = new HashSet<>(foodList);
            combo.setFoods(foodSet);
        }

        Combo result = comboRepository.save(combo);
        return result;
    }

    @Override
    public Combo merge(Combo combo, ComboRequest comboRequest) {
        combo.setName(comboRequest.getName());
        combo.setDescription(comboRequest.getDescription());
        combo.setImage(comboRequest.getImage());
        combo.setPrice(comboRequest.getPrice());
        combo.setCarbonhydrates(comboRequest.getCarbonhydrates());
        combo.setProtein(comboRequest.getProtein());
        combo.setLipid(comboRequest.getLipid());
        combo.setXenluloza(comboRequest.getXenluloza());
        combo.setCanxi(comboRequest.getCanxi());
        combo.setIron(comboRequest.getIron());
        combo.setZinc(comboRequest.getZinc());
        combo.setVitaminA(comboRequest.getVitaminA());
        combo.setVitaminB(comboRequest.getVitaminB());
        combo.setVitaminC(comboRequest.getVitaminC());
        combo.setVitaminD(comboRequest.getVitaminD());
        combo.setVitaminE(comboRequest.getVitaminE());
        combo.setCalorie(comboRequest.getCalorie());
        combo.setWeight(comboRequest.getWeight());
        combo.setStatus(Status.ACTIVE.getValue());

        List<Category> categories = categoryService.findAllByIdIn(comboRequest.getCategoryIds());
        Set<Category> categorySet = new HashSet<>(categories);
        combo.setCategories(categorySet);

        List<Food> foodList = foodService.findAllByIdIn(comboRequest.getFoodIds());
        Set<Food> foodSet = new HashSet<>(foodList);
        combo.setFoods(foodSet);

        Combo result = comboRepository.save(combo);
        return result;
    }

    @Override
    public Combo findByStatusAndId(Integer status, Long id) {
        return comboRepository.findByStatusAndId(status, id);
    }

    @Override
    public Page<Combo> foodsWithPaginate(Specification specification, int page, int limit) {
        return comboRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }
}
