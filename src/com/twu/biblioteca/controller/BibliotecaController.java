package com.twu.biblioteca.controller;

import com.twu.biblioteca.exceptions.InvalidBookException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BibliotecaService;

import java.util.Scanner;

public class BibliotecaController {

    private BibliotecaService bibliotecaService = new BibliotecaService();

    public String welcome() {
        return bibliotecaService.welcome();
    }

    public String showMenu() {
        return bibliotecaService.showMenu();
    }

    public boolean isTheCustomerWannaMoveOn(String choice) {
        Scanner scanner = new Scanner(System.in);
        try {
            switch (choice) {
                case "0":
                    return false;
                case "1":
                    System.out.println(bibliotecaService.displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(bibliotecaService.checkOutBook(scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(bibliotecaService.returnBook(scanner.nextLine()));
                    return true;
                default:
                    bibliotecaService.handleWrongService();
                    return true;
            }
        } catch (InvalidOptionException| InvalidBookException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
