package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ClientService;

import java.util.Scanner;

public class DeleteClientActionProcessor implements ActionProcessor {
    ClientService clientService = new ClientService();

    public boolean process() {
        Scanner delete = new Scanner(System.in);
        System.out.println("Enter client id:");
        long clientId = delete.nextLong();
        clientService.delete(clientId);
        return true;
    }
}
