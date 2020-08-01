package com.twu.biblioteca.entity;

public class Librarian {

    public boolean checkOutBook(Book chosenBook) {
        Biblioteca biblioteca = new Biblioteca();
        return biblioteca.getBookList().stream()
            .map(Book::getBookName)
            .anyMatch(book -> book.equals(chosenBook.getBookName()));
    }
}
