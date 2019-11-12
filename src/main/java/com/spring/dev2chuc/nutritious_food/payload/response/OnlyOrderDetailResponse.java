package com.spring.dev2chuc.nutritious_food.payload.response;

import com.spring.dev2chuc.nutritious_food.helper.DateTimeHelper;
import com.spring.dev2chuc.nutritious_food.model.Combo;
import com.spring.dev2chuc.nutritious_food.model.Food;
import com.spring.dev2chuc.nutritious_food.model.OrderDetail;
import com.spring.dev2chuc.nutritious_food.model.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private OnlyFoodResponse food;
    private OnlyComboResponse combo;
    private OnlyScheduleResponse schedule;


    public OnlyOrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.foodId = orderDetail.getFood() != null ? orderDetail.getFood().getId() : null;
        this.comboId = orderDetail.getCombo() != null ? orderDetail.getCombo().getId() : null;
        this.scheduleId = orderDetail.getSchedule() != null ? orderDetail.getSchedule().getId() : null;
        this.quantity = orderDetail.getQuantity();
        this.type = orderDetail.getType();
        this.price = orderDetail.getPrice();
        this.status = orderDetail.getStatus();
        this.food = orderDetail.getFood() != null ? new OnlyFoodResponse(orderDetail.getFood()) : null;
        this.combo = orderDetail.getCombo() != null ? new OnlyComboResponse(orderDetail.getCombo()) : null;
        this.schedule = orderDetail.getSchedule() != null ? new OnlyScheduleResponse(orderDetail.getSchedule()) : null;
        this.createdAt = DateTimeHelper.formatDateFromLong(orderDetail.getCreatedAt());
        this.updatedAt = DateTimeHelper.formatDateFromLong(orderDetail.getUpdatedAt());
    }
}
