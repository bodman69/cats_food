package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ClientService;

import java.util.Scanner;

public class CreateClientActionProcessor implements ActionProcessor {
    ClientService clientService = new ClientService();

    @Override
    public boolean process() {
        Scanner createClient = new Scanner(System.in);
        System.out.println("Enter client name:");
        String name = createClient.nextLine();
        System.out.println("Enter client phone number:");
        String phoneNumber = createClient.nextLine();
        clientService.save(name, phoneNumber);
        return true;
    }
}
