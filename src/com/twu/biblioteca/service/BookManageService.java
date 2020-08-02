package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.exceptions.InvalidBookException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.repository.BookManageRepository;

import java.util.List;

public class BookManageService {

    BookManageRepository bRepo = new BookManageRepository();

    public List<Book> getValidBooks() {
        return bRepo.getValidBooks();
    }

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String displayBooks() {
        return String.format("%25s %20s %20s\n", "Book Name", "Author", "Published Year") +
            getValidBooks().stream().map(book ->
                String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    public String handleBook(String bookType, String book) {
        String saveType = bookType.equals("valid") ? "lent" : "valid";
        if (bRepo.getBookByBookNameFromList(bookType, book) != null) {
            bRepo.saveBookByBookName(saveType, book);
            bRepo.removeBookByBookName(bookType, book);
            return bookType.equals("valid") ? "Thank you! Enjoy the book." : "Thank you for returning the book!";
        } else {
            throw new InvalidBookException(bookType.equals("valid") ?
                "Sorry, that book is not available." : "This is not a valid book to return.");
        }
    }

    public String quit() {
        return null;
    }

    public void handleWrongService() throws InvalidOptionException {
        throw new InvalidOptionException("Please select a valid option!");
    }

}
