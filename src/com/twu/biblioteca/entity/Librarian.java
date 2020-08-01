package com.twu.biblioteca.entity;

public class Librarian {
    private Biblioteca biblioteca = new Biblioteca();

    public boolean checkOutBook(Book chosenBook) {
        boolean ifAvailable = biblioteca.getBookList().stream()
            .map(Book::getBookName)
            .anyMatch(book -> book.equals(chosenBook.getBookName()));
        if (ifAvailable) {
            removeTheCheckOutBook(chosenBook);
        }
        return ifAvailable;
    }

    private void removeTheCheckOutBook(Book chosenBook) {
        biblioteca.removeBook(chosenBook);
    }
}
