package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;
//import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        System.out.println(biblioteca.welcome());
        do {
            System.out.println(biblioteca.showMenu());
        }
        while (biblioteca.isTheCustomerWannaMoveOn(scanner.nextLine()));
    }
}