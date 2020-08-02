package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;
import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        System.out.println(biblioteca.welcome());
        System.out.println(biblioteca.showMenu());
        String choice = scanner.nextLine();
        while (isTheCustomerWannaMoveOn(biblioteca, choice)) {
            System.out.println(biblioteca.showMenu());
            choice = scanner.nextLine();
        }
    }

    private static boolean isTheCustomerWannaMoveOn(Biblioteca biblioteca, String choice) {
        Scanner scanner = new Scanner(System.in);
        try {
            switch (choice) {
                case "0":
                    return false;
                case "1":
                    System.out.println(biblioteca.displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(biblioteca.checkOutBook(scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(biblioteca.returnBook(scanner.nextLine()));
                    return true;
                default:
                    biblioteca.handleWrongService();
                    return true;
            }
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
