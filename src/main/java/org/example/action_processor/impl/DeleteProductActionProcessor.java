package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ProductService;

import java.util.Scanner;

public class DeleteProductActionProcessor implements ActionProcessor {
    ProductService productService = new ProductService();

    @Override
    public boolean process() {
        Scanner deleteProduct = new Scanner(System.in);
        System.out.println("Enter product id:");
        long prodId = deleteProduct.nextLong();
        productService.delete(prodId);
        return true;
    }
}
