package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.OrderService;

import java.util.Scanner;

public class DeleteOrderActionProcessor implements ActionProcessor {
    OrderService orderService = new OrderService();

    @Override
    public boolean process() {
        Scanner deleteOrder = new Scanner(System.in);
        System.out.println("Enter order id:");
        long orderId = deleteOrder.nextLong();
        orderService.deleteOrder(orderId);
        return true;
    }
}
