package com.twu.biblioteca.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Librarian {

    public boolean checkOutBook(List<Book>bookList, String chosenBook) {
        return bookList.stream()
            .map(Book::getBookName)
            .anyMatch(book -> book.equals(chosenBook));
    }

    public List<Book> removeTheCheckOutBook(List<Book> bookList, String chosenBook) {
        return bookList.stream()
            .filter(book -> !book.getBookName().equals(chosenBook))
            .collect(Collectors.toList());
    }
}
