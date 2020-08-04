package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.exceptions.InvalidProductException;

import java.util.Scanner;

public class Client {

    private ManageController manageController = new ManageController();
    private Scanner scanner = new Scanner(System.in);

    public void startService() {
        System.out.println(manageController.welcome());
        String chosenService = scanner.nextLine();
        do {
            manageController.showMenu();
        } while (isTheCustomerWannaMoveOn(chosenService));
    }

    private boolean isTheCustomerWannaMoveOn(String choice) {
        try {
            switch (choice) {
                case "0":
                    return manageController.quit();
                case "1":
                    System.out.println(manageController.displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(manageController.checkOutBook(scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(manageController.returnBook(scanner.nextLine()));
                    return true;
                case "4":
                    System.out.println(manageController.displayMovies());
                    return true;
                case "5":
                    System.out.println("Please enter the movie name you want:");
                    System.out.println(manageController.checkoutMovie(scanner.nextLine()));
                    return true;
                default:
                    manageController.handleWrongService();
                    return true;
            }
        } catch (InvalidOptionException | InvalidProductException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
