package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ProductService;

public class FindAllProductActionProcessor implements ActionProcessor {
    ProductService productService = new ProductService();

    @Override
    public boolean process() {
        System.out.println(productService.findAll());
        return true;
    }
}
