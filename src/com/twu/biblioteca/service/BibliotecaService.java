package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Librarian;
import com.twu.biblioteca.exceptions.InvalidBookException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.repository.BibliotecaRepository;

import java.util.List;

public class BibliotecaService {

    BibliotecaRepository bRepo = new BibliotecaRepository();
    Librarian librarian = new Librarian();

    public List<Book> getBookList() {
        return bRepo.getBookList();
    }

    private List<Book> getLentBookList() {
        return bRepo.getLentBookList();
    }

    public String showMenu() {
        return "====MENU====\n1. List of books\n2. Check out book\n3. Return book\n0. Quit\nChoose the service you want:";
    }

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String displayBooks() {
        return String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year") +
            getBookList().stream().map(book ->
                String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    public String checkOutBook(String book) {
        boolean ifAvailable = librarian.checkBookState(getBookList(), book);
        if (ifAvailable) {
            bRepo.setLentBookList(librarian.addBook(getBookList(), getLentBookList(), book));
            bRepo.setBookList(librarian.removeBook(getBookList(), book));
            return "Thank you! Enjoy the book.";
        } else {
            throw new InvalidBookException("Sorry, that book is not available.");
        }
    }

    public String returnBook(String book) {
        boolean ifLent = librarian.checkBookState(getLentBookList(), book);
        if (ifLent) {
            bRepo.setBookList(librarian.addBook(getLentBookList(), getBookList(), book));
            bRepo.setLentBookList(librarian.removeBook(getLentBookList(), book));
            return "Thank you for returning the book!";
        }
        return "This is not a valid book to return.";
    }

    public String quit() {
        return null;
    }

    public void handleWrongService() throws InvalidOptionException {
        throw new InvalidOptionException("Please select a valid option!");
    }

}
