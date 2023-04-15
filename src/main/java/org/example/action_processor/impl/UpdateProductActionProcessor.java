package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class UpdateProductActionProcessor implements ActionProcessor {
    ProductService productService = new ProductService();

    @Override
    public boolean process() {
        Scanner updateProduct = new Scanner(System.in);
        System.out.println("Enter product id:");
        long productId = updateProduct.nextLong();
        boolean ask = true;
        while (ask) {
            System.out.println("Enter an action:");
            String field = updateProduct.nextLine();
            switch (field) {
                case "change product name":
                    System.out.println("Enter product new name:");
                    String newName = updateProduct.nextLine();
                    productService.update(productId, newName, null, null);
                    break;
                case "change product count":
                    System.out.println("Enter product new count:");
                    Long newProductCount = updateProduct.nextLong();
                    productService.update(productId, null, newProductCount, null);
                    break;
                case "change product price":
                    System.out.println("Enter new product price:");
                    BigDecimal newProductPrice = updateProduct.nextBigDecimal();
                    productService.update(productId, null, null, newProductPrice);
                    break;
                case "save":
                    ask = false;
                    break;
                default:
                    System.out.println("select an action");
                    break;
            }
        }
        return true;
    }
}
