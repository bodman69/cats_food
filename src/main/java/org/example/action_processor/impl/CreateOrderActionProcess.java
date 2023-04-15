package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.model.OrderItem;
import org.example.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateOrderActionProcess implements ActionProcessor {
    OrderService orderService = new OrderService();
    List<OrderItem> orderItemList = new ArrayList<>();
    @Override
    public boolean process() {
        Scanner create = new Scanner(System.in);
        System.out.println("Enter client id:");
        long customerId = create.nextLong();
        orderService.createOrder(customerId, orderItemList);
        return true;
    }
}
