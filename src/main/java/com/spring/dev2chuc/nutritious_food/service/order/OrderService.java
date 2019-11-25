package com.spring.dev2chuc.nutritious_food.service.order;

import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.OrderRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllByUser(User user);

    OrderDTO saveOrderByUser(OrderRequest orderRequest);

    OrderDTO getById(Long id);
}
