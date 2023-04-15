package org.example.service;

import org.example.entity.ProductEntity;
import org.example.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    public static final String A = "\u001B[36m";
    public static final String R = "\u001B[0m";
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public void save(String name, long count, BigDecimal price) {
        if (productRepository.existProductByName(name)) {
            System.out.println("Product with name " + A + name + R + " already exist");
        } else {
            productRepository.saveProduct(name, count, price);
        }
    }

    public ProductEntity findProduct(long id) {
        return productRepository.findProductById(id);
    }

    public String getProductName(long productId) {
        return productRepository.findProductNameById(productId);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAllProducts();
    }

    public void update(long id, String newName, Long newCount, BigDecimal newPrice) {
        ProductEntity productEntity = productRepository.findProductById(id);
        if (newName != null && !newName.equals("")) {
            productEntity.setName(newName);
        }
        if (newCount != null) {
            productEntity.setCount(newCount);
        }
        if (newPrice != null) {
            productEntity.setPrice(newPrice);
        }
        productRepository.changeProductById(productEntity);
    }

    public void delete(long id) {
        if (!productRepository.existProductById(id)) {
            System.err.println("Product not exist");
        } else {
            productRepository.deleteProductById(id);
        }
    }

    public List<ProductEntity> findAllByProductIdIn(List<Long> productIds) {
        String ids = productIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return productRepository.findAllByProductIdIn(ids);
    }
}
