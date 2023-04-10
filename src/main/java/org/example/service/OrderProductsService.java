package org.example.service;

import org.example.entity.OrderEntity;
import org.example.entity.OrderProductEntity;
import org.example.model.OrderItem;
import org.example.repository.OrderProductRepository;

import java.util.List;

public class OrderProductsService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductsService() {
        this.orderProductRepository = new OrderProductRepository();
    }

    public void saveOrderItem(OrderProductEntity orderProductEntity) {
        orderProductRepository.save(orderProductEntity);
    }
}
