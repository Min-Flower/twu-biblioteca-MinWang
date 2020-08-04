package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.exceptions.InvalidProductException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.repository.BookManageRepository;

import java.util.List;

public class BookManageService {

    BookManageRepository bRepo = new BookManageRepository();

    public List<Book> getValidBooks() {
        return bRepo.getValidBooks();
    }

    public String displayBooks() {
        return String.format("%25s %20s %20s\n", "Book Name", "Author", "Published Year") +
            getValidBooks().stream().map(book ->
                String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    public String handleBook(String bookType, String book) {
        if (bRepo.getBookByBookName(bookType, book)) {
            return bookType.equals("valid") ? "Thank you! Enjoy the book." : "Thank you for returning the book!";
        } else {
            throw new InvalidProductException(bookType.equals("valid") ?
                "Sorry, that book is not available." : "This is not a valid book to return.");
        }
    }
}
