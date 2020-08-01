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
        while (isTheOptionInvalid(biblioteca, choice)) {
            System.out.println(biblioteca.showMenu());
            choice = scanner.nextLine();
        }
    }

    private static boolean isTheOptionInvalid(Biblioteca biblioteca, String choice) {
        try {
            if(biblioteca.chooseService(choice) != null) {
                System.out.println(biblioteca.chooseService(choice));
                return true;
            }
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }
}
