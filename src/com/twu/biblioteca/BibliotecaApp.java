package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        System.out.println(biblioteca.welcome());
        System.out.println(biblioteca.showMenu());
        System.out.println(biblioteca.chooseService(scanner.nextLine()));
    }
}
