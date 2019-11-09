package com.spring.dev2chuc.nutritious_food.service.order;

import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.payload.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllByUser(User user);
    OrderResponse saveOrderByUser(User user, List<OrderRequest> orderRequest);

    OrderResponse getById(Long id);
}
