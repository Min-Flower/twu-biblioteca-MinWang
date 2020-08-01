package com.twu.biblioteca.entity;

import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    private List<Book> bookList = initBookList();
    private Librarian librarian = new Librarian();

    public Biblioteca() {}

    private List<Book> initBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        bookList.add(new Book("The Red and the Black", "Stendhal", 1830));
        bookList.add(new Book("War and Peace", "Tolstoy", 1869));
        bookList.add(new Book("David Copperfield", "Charles Dickens", 1849));
        return bookList;
    }

    public String showMenu() {
        return "====MENU====\n1. List of books\n2. Check out book\n0. Quit\nChoose the service you want:";
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String displayBooks() {
        return String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year") +
            this.bookList.stream().map(book ->
            String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
            .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

//    public String checkService() {
//        Scanner scanner = new Scanner(System.in);
//        if (checkOutBook(scanner.nextLine())) {
//            return "Thank you! Enjoy the book";
//        }
//        return "Sorry, that book is not available";
//    }

    public String checkOutBook(String book) {
        boolean ifAvailable = librarian.checkOutBook(bookList, book);
        if (ifAvailable) {
            this.bookList = librarian.removeTheCheckOutBook(bookList, book);
            return "Thank you! Enjoy the book";
        }
        return "Sorry, that book is not available";
    }

    public String chooseService(String choice) throws InvalidOptionException {
        switch (choice) {
            case "1":
                return displayBooks();
            case "0":
                return null;
            default:
                throw new InvalidOptionException("Please select a valid option!");
        }
    }
}
