package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        ManageController biblioteca = new ManageController();
        Scanner scanner = new Scanner(System.in);
        System.out.println(biblioteca.welcome());
        do {
            biblioteca.showMenu();
        }
        while (biblioteca.isTheCustomerWannaMoveOn(scanner.nextLine()));
    }
}