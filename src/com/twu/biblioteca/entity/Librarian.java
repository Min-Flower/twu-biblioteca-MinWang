package com.twu.biblioteca.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Librarian {

    public boolean checkBookState(List<Book>bookList, String chosenBook) {
        return bookList.stream()
            .map(Book::getBookName)
            .anyMatch(book -> book.equals(chosenBook));
    }

    public List<Book> addBook(List<Book> addList, List<Book> removeList, String chosenBook) {
        addList.stream()
            .filter(book -> book.getBookName().equals(chosenBook))
            .findFirst().ifPresent(removeList::add);
        return addList;
    }

    public List<Book> removeBook(List<Book> removeList, String chosenBook) {
        return removeList.stream()
            .filter(book -> !book.getBookName().equals(chosenBook))
            .collect(Collectors.toList());
    }

}
