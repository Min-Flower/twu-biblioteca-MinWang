package com.twu.biblioteca.controller;

import com.twu.biblioteca.exceptions.InvalidBookException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BookManageService;
import com.twu.biblioteca.service.MovieManageService;

import java.util.Scanner;

public class ManageController {

    private BookManageService bookManageService = new BookManageService();
    private MovieManageService movieManageService = new MovieManageService();

    public String welcome() {
        return bookManageService.welcome();
    }

    public void showMenu() {
        String[] menu = {"====MENU====", "1. List of books", "2. Check out book",
            "3. Return book", "4. List of Movies", "0. Quit", "Choose the service you want:"};
        for (String item : menu) {
            System.out.println(item);
        }
    }

    public boolean isTheCustomerWannaMoveOn(String choice) {
        Scanner scanner = new Scanner(System.in);
        try {
            switch (choice) {
                case "0":
                    return false;
                case "1":
                    System.out.println(bookManageService.displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(bookManageService.handleBook("valid", scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(bookManageService.handleBook("lent", scanner.nextLine()));
                    return true;
                case "4":
                    System.out.println(movieManageService.displayMovies());
                    return true;
                default:
                    bookManageService.handleWrongService();
                    return true;
            }
        } catch (InvalidOptionException | InvalidBookException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
