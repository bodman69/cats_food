package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ClientService;

import java.util.Scanner;

public class UpdateClientActionProcessor implements ActionProcessor {
    ClientService clientService = new ClientService();

    @Override
    public boolean process() {
        Scanner update = new Scanner(System.in);
        System.out.println("Enter client id:");
        long id = update.nextLong();
        boolean askAction = true;
        while (askAction) {
            System.out.println("Enter an action:");
            String field = update.nextLine();
            switch (field) {
                case "change name":
                    System.out.println("Enter client new name:");
                    String newName = update.nextLine();
                    clientService.update(id, newName, null);
                    break;
                case "change phone_number":
                    System.out.println("Enter client new phone_number:");
                    String newPhoneNumber = update.nextLine();
                    clientService.update(id, null, newPhoneNumber);
                    break;
                case "save":
                    askAction = false;
                    break;
                default:
                    System.out.println("No action selected");
                    break;
            }
        }
        return true;
    }
}
