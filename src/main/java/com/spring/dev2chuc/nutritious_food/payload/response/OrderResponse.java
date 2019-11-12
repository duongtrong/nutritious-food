package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.helper.DateTimeHelper;
import com.spring.dev2chuc.nutritious_food.model.Order;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyOrderDetailResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderResponse {
    private Long id;
    private Long userId;
    private String userName;
    private float totalPrice;
    private int type;
    private int status;
    private String createdAt;
    private String updatedAt;

    private Set<OnlyOrderDetailResponse> orderDetail;

    public OrderResponse(Order order, Set<OnlyOrderDetailResponse> orderDetails) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.userName = order.getUser().getUsername();
        this.totalPrice = order.getTotalPrice();
        this.type = order.getType();
        this.status = order.getStatus();
        this.orderDetail = orderDetails;
        this.createdAt = DateTimeHelper.formatDateFromLong(order.getCreatedAt());
        this.updatedAt = DateTimeHelper.formatDateFromLong(order.getUpdatedAt());
    }

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.userId = order.getUser().getId();
        this.userName = order.getUser().getUsername();
        this.totalPrice = order.getTotalPrice();
        this.type = order.getType();
        this.status = order.getStatus();
        this.orderDetail = order.getOrderDetails().stream().map(orderDetail -> new OnlyOrderDetailResponse(orderDetail)).collect(Collectors.toSet());
        this.createdAt = DateTimeHelper.formatDateFromLong(order.getCreatedAt());
        this.updatedAt = DateTimeHelper.formatDateFromLong(order.getUpdatedAt());
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<OnlyOrderDetailResponse> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(Set<OnlyOrderDetailResponse> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOnlyOrderDetailResponses(Set<OnlyOrderDetailResponse> onlyOrderDetailResponses) {
        this.orderDetail = onlyOrderDetailResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
