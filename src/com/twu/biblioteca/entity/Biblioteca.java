package com.twu.biblioteca.entity;

import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private List<Book> bookList = initBookList();

    public Biblioteca() {}

    private List<Book> initBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        bookList.add(new Book("The Red and the Black", "Stendhal", 1830));
        bookList.add(new Book("War and Peace", "Tolstoy", 1869));
        bookList.add(new Book("David Copperfield", "Charles Dickens", 1849));
        return bookList;
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public void removeBook(Book chosenBook) {
        this.bookList = this.getBookList().stream()
            .filter(book -> !book.getBookName().equals(chosenBook.getBookName()))
            .collect(Collectors.toList());
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

    public String showMenu() {
        return "====MENU====\n1. List of books\n0. Quit\nChoose the service you want:";
    }

    public String chooseService(String choice) throws InvalidOptionException {
        if (choice.equals("1")) {
            return displayBooks();
        } else if (choice.equals("0")){
            return null;
        } else {
            throw new InvalidOptionException("Please select a valid option!");
        }
    }
}
