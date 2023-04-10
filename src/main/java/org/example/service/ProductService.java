package org.example.service;

import org.example.entity.ClientEntity;
import org.example.entity.ProductEntity;
import org.example.repository.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public void save(String name, long count, double price) {
        if (productRepository.existProductByName(name)) {
            System.out.println("Product with name " + A + name + R + " already exist");
        } else {
            productRepository.saveProduct(name, count, price);
        }
    }
    public ProductEntity findProduct(long id){
        return productRepository.findProductById(id);
    }
    public List<ProductEntity> findAll() {
        return productRepository.findAllProducts();
    }

    public void update(long id, String name, long count, double price) {
        productRepository.changeProductById(id, name, count, price);
    }

    public void updateCount(long productId, long newProductCount){
        productRepository.changeProductCount(productId, newProductCount);
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



    public static final String A = "\u001B[36m";
    public static final String R = "\u001B[0m";
}
