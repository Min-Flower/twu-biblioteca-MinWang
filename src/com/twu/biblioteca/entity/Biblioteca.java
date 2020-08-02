package com.twu.biblioteca.entity;

import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Book> bookList = initBookList();
    private List<Book> lentBookList = new ArrayList<>();
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
        return "====MENU====\n1. List of books\n2. Check out book\n3. Return book\n0. Quit\nChoose the service you want:";
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

    public String quit() {
        return null;
    }

    public void handleWrongService() throws InvalidOptionException {
        throw new InvalidOptionException("Please select a valid option!");
    }

    public String checkOutBook(String book) {
        boolean ifAvailable = librarian.checkBookState(bookList, book);
        if (ifAvailable) {
            this.lentBookList = librarian.addLentBook(bookList, lentBookList, book);
            this.bookList = librarian.removeTheCheckOutBook(bookList, book);
            return "Thank you! Enjoy the book";
        }
        return "Sorry, that book is not available";
    }

    public String returnBook(String book) {
        boolean ifLent = librarian.checkBookState(lentBookList, book);
        if (ifLent) {
            this.bookList = librarian.getBackBook(bookList, lentBookList, book);
            this.lentBookList = librarian.removeTheLentBook(lentBookList, book);
            return "Thank you for returning the book!";
        }
        return "This is not a valid book to return.";
    }

}
