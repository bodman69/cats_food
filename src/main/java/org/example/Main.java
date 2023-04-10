package org.example;

import org.example.entity.ClientEntity;
import org.example.entity.OrderEntity;
import org.example.model.OrderItem;
import org.example.repository.ClientRepository;
import org.example.service.ClientService;
import org.example.service.OrderService;
import org.example.service.ProductService;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
/*        ClientService client = new ClientService();
        ProductService product = new ProductService();
//1 -> this::createClient
        Scanner scanner = new Scanner(System.in);
        boolean askCommand = true;
        while (askCommand) {
            System.out.println("Select an actions number :");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    Scanner details = new Scanner(System.in);
                    System.out.println("Enter client name :");
                    String name = details.nextLine();
                    System.out.println("Enter client phone number :");
                    String phoneNumber = details.nextLine();
                    client.save(name, phoneNumber);
                    break;
                case 2:
                    System.out.println(client.findAll());
                    break;
                case 3:
                    Scanner update = new Scanner(System.in);
                    System.out.println("Enter client id :");
                    long id = update.nextLong();
                    boolean askAction = true;
                    while (askAction) {
                        System.out.println("Enter an action :");
                        String field = update.nextLine();
                        switch (field) {
                            case "change name":
                                System.out.println("Enter client new name :");
                                String newName = update.nextLine();
                                client.update(id, newName, null);
                                break;
                            case "change phone_number":
                                System.out.println("Enter client new phone_number :");
                                String newPhoneNumber = update.nextLine();
                                client.update(id, null, newPhoneNumber);
                                break;
                            case "save":
                                askAction = false;
                            default:
                                System.out.println("No action selected");
                                break;
                        }
                    }
                case 4:

                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    askCommand = false;
            }
        }

 */

        List<OrderItem> orderItemList = Arrays.asList(
                new OrderItem(1,1),
                new OrderItem(2,1)
        );


        OrderService orderService = new OrderService();
        orderService.createOrder(43,orderItemList);
    }
}