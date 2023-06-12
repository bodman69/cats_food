package com.lesson.shop.service;

import com.lesson.shop.model.entity.OrderProductEntity;
import com.lesson.shop.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public void save(List<OrderProductEntity> orderProductEntityList) {
        orderProductRepository.saveAll(orderProductEntityList);
    }

    public void deleteOrderProduct(Long id) {
        orderProductRepository.deleteByOrderId(id);
    }
}
