package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateProductActionProcessor implements ActionProcessor {
    ProductService productService = new ProductService();

    public boolean process() {
        Scanner createProduct = new Scanner(System.in);
        System.out.println("Enter product name:");
        String productName = createProduct.nextLine();
        System.out.println("Enter number of products:");
        long productCount = createProduct.nextLong();
        System.out.println("Enter price for one product:");
        BigDecimal productPrice = createProduct.nextBigDecimal();
        productService.save(productName, productCount, productPrice);
        return true;
    }
}
