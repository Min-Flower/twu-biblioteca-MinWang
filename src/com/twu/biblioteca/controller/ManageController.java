package com.twu.biblioteca.controller;

import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BookManageService;
import com.twu.biblioteca.service.MovieManageService;

public class ManageController {

    private BookManageService bookManageService = new BookManageService();
    private MovieManageService movieManageService = new MovieManageService();

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public void showMenu() {
        String[] menu = {"====MENU====", "1. List of books", "2. Check out book",
            "3. Return book", "4. List of Movies", "5. Check out movie", "0. Quit", "Choose the service you want:"};
        for (String item : menu) {
            System.out.println(item);
        }
    }

    public String displayBooks() {
        return bookManageService.displayBooks();
    }

    public String checkOutBook(String bookName) {
        return bookManageService.handleBook("valid", bookName);
    }

    public String returnBook(String bookName) {
        return bookManageService.handleBook("lent", bookName);
    }

    public String displayMovies() {
        return movieManageService.displayMovies();
    }

    public String checkoutMovie(String movieName) {
        return movieManageService.checkoutMovie(movieName);
    }

    public void handleWrongService() throws InvalidOptionException {
        throw new InvalidOptionException("Please select a valid option!");
    }

    public boolean quit() {
        return false;
    }

}
