package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.exceptions.InvalidProductException;

import java.util.Scanner;

public class Client {

    private ManageController manageController = new ManageController();
    private Scanner scanner = new Scanner(System.in);

    public void startService() {
        System.out.println("Choose Your Identity:");
        System.out.println("1. Librarian");
        System.out.println("2. Customer");
        login(scanner.nextLine());
    }

    private void login(String identity) {
        System.out.println("==============================");
        System.out.println(manageController.welcome());
        switch (identity) {
            case "1":
                librarianService();
                break;
            case "2":
               userService();
               break;
            default:
                try {
                    throw new InvalidOptionException("Please check the given identity!");
                } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                }
        }
    }

    private void librarianService() {
        do {
            System.out.println("=====Menu=====");
            System.out.println("1. checkBorrowingRecord");
            System.out.println("0. quit");
        } while (isTheLibrarianWannaQuit(scanner.nextLine()));
    }

    private boolean isTheLibrarianWannaQuit(String choice) {
        switch (choice) {
            case "1":
                System.out.println(checkBorrowingRecord());
                return true;
            case "0":
                return manageController.quit();
            default:
                try {
                    manageController.handleWrongService();
                } catch (InvalidOptionException e) {
                    System.out.println(e.getMessage());
                }
                return true;
        }
    }

    private String checkBorrowingRecord() {
        return manageController.checkBorrowingRecord();
    }

    private void userService() {
        System.out.println("===================================");
        System.out.println("Please input your username and password");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        User userInfo = manageController.getUserInfo(username, password);
        if((userInfo != null)) {
            do {
                System.out.println("Here is Your Personal Information: " + userInfo.toString());
                manageController.showMenu();
            } while (isTheCustomerWannaMoveOn(userInfo, scanner.nextLine()));
        }
    }

    private boolean isTheCustomerWannaMoveOn(User user, String choice) {
        try {
            switch (choice) {
                case "0":
                    return manageController.quit();
                case "1":
                    System.out.println(manageController.displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(manageController.checkOutBook(user.getId(), scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(manageController.returnBook(user.getId(), scanner.nextLine()));
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