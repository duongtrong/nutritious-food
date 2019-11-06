package com.spring.dev2chuc.nutritious_food.payload;

import com.spring.dev2chuc.nutritious_food.helper.DateTimeHelper;
import com.spring.dev2chuc.nutritious_food.model.OrderDetail;

public class OnlyOrderDetailResponse {
    private Long id;
    private Long orderId;
    private Long foodId;
    private Long comboId;
    private Long scheduleId;
    private Integer quantity;
    private Integer type;
    private float price;
    private Integer status;
    private String createdAt;
    private String updatedAt;



    public OnlyOrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.foodId = orderDetail.getFood() != null ? orderDetail.getFood().getId() : null;
        this.comboId = orderDetail.getCombo() != null ? orderDetail.getCombo().getId() : null;
        this.scheduleId = orderDetail.getSchedule() != null ? orderDetail.getSchedule().getId() : null;
        this.quantity = orderDetail.getQuantity();
        this.type = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
        this.status = orderDetail.getStatus();
        this.createdAt = DateTimeHelper.formatDateFromLong(orderDetail.getCreatedAt());
        this.updatedAt = DateTimeHelper.formatDateFromLong(orderDetail.getUpdatedAt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getComboId() {
        return comboId;
    }

    public void setComboId(Long comboId) {
        this.comboId = comboId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
