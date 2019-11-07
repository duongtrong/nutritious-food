package com.spring.dev2chuc.nutritious_food.service.food;

import com.spring.dev2chuc.nutritious_food.model.Category;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.Status;
import com.spring.dev2chuc.nutritious_food.payload.FoodRequest;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import com.spring.dev2chuc.nutritious_food.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public Food merge(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food findById(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(null);
        return food;
    }

    @Override
    public Food update(Food food, FoodRequest foodRequest) {
        if (foodRequest.getName() != null) food.setName(foodRequest.getName());
        if (foodRequest.getDescription() != null) food.setDescription(foodRequest.getDescription());
        if (foodRequest.getImage() != null) food.setImage(foodRequest.getImage());
        if (foodRequest.getPrice() != 0.0f) food.setPrice(foodRequest.getPrice());
        if (foodRequest.getCarbonhydrates() != 0.0f) food.setCarbonhydrates(foodRequest.getCarbonhydrates());
        if (foodRequest.getProtein() != 0.0f) food.setProtein(foodRequest.getProtein());
        if (foodRequest.getLipid() != 0.0f) food.setLipid(foodRequest.getLipid());
        if (foodRequest.getXenluloza() != 0.0f) food.setXenluloza(foodRequest.getXenluloza());
        if (foodRequest.getCanxi() != 0.0f) food.setCanxi(foodRequest.getCanxi());
        if (foodRequest.getIron() != 0.0f) food.setIron(foodRequest.getIron());
        if (foodRequest.getZinc() != 0.0f) food.setZinc(foodRequest.getZinc());
        if (foodRequest.getVitaminA() != 0.0f) food.setVitaminA(foodRequest.getVitaminA());
        if (foodRequest.getVitaminB() != 0.0f) food.setVitaminB(foodRequest.getVitaminB());
        if (foodRequest.getVitaminC() != 0.0f) food.setVitaminC(foodRequest.getVitaminC());
        if (foodRequest.getVitaminD() != 0.0f) food.setVitaminD(foodRequest.getVitaminD());
        if (foodRequest.getVitaminE() != 0.0f) food.setVitaminE(foodRequest.getVitaminE());
        if (foodRequest.getCalorie() != 0.0f) food.setCalorie(foodRequest.getCalorie());

        if (foodRequest.getCateId().size() > 0) {
            List<Category> categories = categoryService.findAllByIdIn(foodRequest.getCateId());
            Set<Category> categorySet = new HashSet<>(categories);
            food.setCategories(categorySet);
        }

        Food result = foodRepository.save(food);
        return result;
    }

    @Override
    public Food merge(Food food, FoodRequest foodRequest) {
        food.setName(foodRequest.getName());
        food.setName(foodRequest.getDescription());
        food.setName(foodRequest.getImage());
        food.setPrice(foodRequest.getPrice());
        food.setCarbonhydrates(foodRequest.getCarbonhydrates());
        food.setProtein(foodRequest.getProtein());
        food.setLipid(foodRequest.getLipid());
        food.setXenluloza(foodRequest.getXenluloza());
        food.setCanxi(foodRequest.getCanxi());
        food.setIron(foodRequest.getIron());
        food.setZinc(foodRequest.getZinc());
        food.setVitaminA(foodRequest.getVitaminA());
        food.setVitaminB(foodRequest.getVitaminB());
        food.setVitaminC(foodRequest.getVitaminC());
        food.setVitaminD(foodRequest.getVitaminD());
        food.setVitaminE(foodRequest.getVitaminE());
        food.setCalorie(foodRequest.getCalorie());
        food.setStatus(Status.ACTIVE.getValue());

        if (foodRequest.getCateId().size() > 0) {
            List<Category> categories = categoryService.findAllByIdIn(foodRequest.getCateId());
            Set<Category> categorySet = new HashSet<>(categories);
            food.setCategories(categorySet);
        }

        Food current = foodRepository.save(food);
        return current;
    }

    @Override
    public List<Food> findAllByIdIn(Collection<Long> Ids) {
        List<Food> list = foodRepository.findAllByIdIn(Ids);
        if (list == null) {
            return null;
        }
        return list;
    }
}
