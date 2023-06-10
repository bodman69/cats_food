package com.lesson.shop.model.request;

import com.lesson.shop.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderUpdateRequest {
    private OrderStatus orderStatus;
    private List<OrderProductRequest> orderProducts;
}
