package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookManageRepository {

    private List<Book> bookList = BookData.bookList;

    public List<Book> getValidBooks() {
        return BookData.bookList.stream()
            .filter(book -> book.getState().equals("valid"))
            .collect(Collectors.toList());
    }

    public boolean getBookByBookName(String bookState, String name) {
        boolean isValid = BookData.bookList.stream()
            .anyMatch(book -> book.getBookName().equals(name)
                && book.getState().equals(bookState));
        if (isValid) {
            changeStateByBookName(name);
        }
        return isValid;
    }

    private void changeStateByBookName(String bookName) {
        bookList.stream()
            .filter(book -> book.getBookName().equals(bookName))
            .forEach(book -> book.setState(book.getState().equals("valid") ? "lent" : "valid"));
    }
}
