package org.example.service;

import org.example.entity.OrderProductEntity;
import org.example.repository.OrderProductRepository;

import java.util.List;

public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService() {
        this.orderProductRepository = new OrderProductRepository();
    }

    public void saveOrderItem(OrderProductEntity orderProductEntity) {
        orderProductRepository.save(orderProductEntity);
    }

    public List<OrderProductEntity> findAll() {
        return orderProductRepository.findAllOrderProducts();
    }
}
