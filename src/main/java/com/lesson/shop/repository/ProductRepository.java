package com.lesson.shop.repository;

import com.lesson.shop.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByPrice(BigDecimal price);

    List<ProductEntity> findByNameAndPrice(String name, BigDecimal price);

    boolean existsByName(String name);

}
