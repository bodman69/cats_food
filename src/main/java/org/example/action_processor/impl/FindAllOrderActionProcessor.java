package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.OrderService;

public class FindAllOrderActionProcessor implements ActionProcessor {
    OrderService orderService = new OrderService();

    @Override
    public boolean process() {
        System.out.println(orderService.getAllOrders());
        return true;
    }
}
