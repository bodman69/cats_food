package org.example;

import org.example.action_processor.ActionProcessor;
import org.example.action_processor.ActionProcessorFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ActionProcessorFactory processorFactory = new ActionProcessorFactory();
        Scanner scanner = new Scanner(System.in);
        boolean askCommand = true;
        while (askCommand) {
            System.out.println("Select an actions number:");
            int input = scanner.nextInt();

            ActionProcessor actionProcessor = processorFactory.getActionProcessor(input);
            if (actionProcessor == null) {
                System.err.println("choose action from 1 to 11");
            } else {
                askCommand = actionProcessor.process();
            }
        }
    }
}