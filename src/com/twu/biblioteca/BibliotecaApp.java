package com.twu.biblioteca;

import com.twu.biblioteca.controller.BibliotecaController;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaController biblioteca = new BibliotecaController();
        Scanner scanner = new Scanner(System.in);
        System.out.println(biblioteca.welcome());
        do {
            System.out.println(biblioteca.showMenu());
        }
        while (biblioteca.isTheCustomerWannaMoveOn(scanner.nextLine()));
    }
}