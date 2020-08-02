package com.twu.biblioteca.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Librarian {

    public boolean checkBookState(List<Book>bookList, String chosenBook) {
        return bookList.stream()
            .map(Book::getBookName)
            .anyMatch(book -> book.equals(chosenBook));
    }

    public List<Book> addLentBook(List<Book> bookList, List<Book> lentBookList, String book) {
        bookList.stream()
            .filter(b -> b.getBookName().equals(book))
            .findFirst().ifPresent(lentBookList::add);
        return lentBookList;
    }

    public List<Book> removeTheCheckOutBook(List<Book> bookList, String chosenBook) {
        return bookList.stream()
            .filter(book -> !book.getBookName().equals(chosenBook))
            .collect(Collectors.toList());
    }

    public List<Book> getBackBook(List<Book> bookList, List<Book> lentBookList, String book) {
        lentBookList.stream()
            .filter(b -> b.getBookName().equals(book))
            .findFirst().ifPresent(bookList::add);
        return bookList;
    }

    public List<Book> removeTheLentBook(List<Book> lentBookList, String book) {
        return lentBookList.stream()
            .filter(b -> !b.getBookName().equals(book))
            .collect(Collectors.toList());
    }
}
