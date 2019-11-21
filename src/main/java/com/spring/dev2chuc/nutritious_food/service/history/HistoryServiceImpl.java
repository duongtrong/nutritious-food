package com.spring.dev2chuc.nutritious_food.service.history;

import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.History;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.HistoryRequest;
import com.spring.dev2chuc.nutritious_food.repository.FoodRepository;
import com.spring.dev2chuc.nutritious_food.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List<History> findAllByUser(User user) {
        return historyRepository.findAllByUser(user);
    }

    @Override
    public History getDetailById(Long id) {
        return historyRepository.findById(id).orElseThrow(null);
    }

    @Override
    public History store(HistoryRequest historyRequest, User user, Food food) {
        History history = new History(historyRequest.getCalorie(), historyRequest.getComment(), historyRequest.getType(), user, food);
        return historyRepository.save(history);
    }


}
