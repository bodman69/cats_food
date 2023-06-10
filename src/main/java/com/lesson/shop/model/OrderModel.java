package com.lesson.shop.model;

import com.lesson.shop.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long orderId;
    private String clientName;
    private Date date;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
}
