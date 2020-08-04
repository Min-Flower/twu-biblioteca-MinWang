package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookManageRepository {

    private List<Book> bookList = BookData.bookList;

    public List<Book> getBooksByType(String bookType) {
        return BookData.bookList.stream()
            .filter(book -> book.getState().equals(bookType))
            .collect(Collectors.toList());
    }

    public boolean handleBook(String userId, String bookState, String name) {
        boolean isValid = BookData.bookList.stream()
            .anyMatch(book -> book.getBookName().equals(name)
                && book.getState().equals(bookState)
                && (book.getState().equals("valid") || book.getUserId().equals(userId)));
        if (isValid) {
            changeStateByBookName(userId, name);
        }
        return isValid;
    }

    private void changeStateByBookName(String userId, String bookName) {
        bookList.stream()
            .filter(book -> book.getBookName().equals(bookName))
            .forEach(book -> {
                book.setState(book.getState().equals("valid") ? "lent" : "valid");
                book.setUserId(book.getUserId() == null ? userId : null);
            });
    }

}
