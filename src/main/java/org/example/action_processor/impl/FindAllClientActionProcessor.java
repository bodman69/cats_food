package org.example.action_processor.impl;

import org.example.action_processor.ActionProcessor;
import org.example.service.ClientService;

public class FindAllClientActionProcessor implements ActionProcessor {
    ClientService clientService = new ClientService();

    @Override
    public boolean process() {
        System.out.println(clientService.findAll());
        return true;
    }
}
