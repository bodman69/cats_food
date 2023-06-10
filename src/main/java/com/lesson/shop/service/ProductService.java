package com.lesson.shop.service;

import com.lesson.shop.exception.EntityNotFoundException;
import com.lesson.shop.model.entity.ProductEntity;
import com.lesson.shop.model.request.ProductRequest;
import com.lesson.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity create(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new EntityNotFoundException("Product with name " + request.getName() + " already exist");
        }

        ProductEntity product = ProductEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .count(request.getCount())
                .build();

        return productRepository.save(product);
    }

    public void saveAll(List<ProductEntity> productEntities) {
        productRepository.saveAll(productEntities);
    }

    public List<ProductEntity> getAll(String name, BigDecimal price) {
        if (name == null && price == null) {
            return productRepository.findAll();
        }
        if (name != null && price == null) {
            return productRepository.findByName(name);
        }
        if (name == null && price != null) {
            return productRepository.findByPrice(price);
        }

        return productRepository.findByNameAndPrice(name, price);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with id " + id + " doesn't exist");
        }
        productRepository.deleteById(id);
    }


    public void update(Long id, ProductRequest request) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " doesn't exist"));

        product.setCount(request.getCount());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }

    public List<ProductEntity> findAllByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

}
