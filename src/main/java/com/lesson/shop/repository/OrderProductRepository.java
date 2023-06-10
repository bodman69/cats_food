package com.lesson.shop.repository;

import com.lesson.shop.model.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
    void deleteByOrderId(Long id);
}
