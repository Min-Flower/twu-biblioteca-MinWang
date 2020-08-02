package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.entity.Book;

import java.util.List;

public class BookManageRepository {

    private List<Book> bookList = BookData.bookList;
    private List<Book> vBookList = BookData.validBookList;
    private List<Book> lBookList = BookData.lentBookList;

    public List<Book> getAllBooks() {
        return bookList;
    }

    public List<Book> getValidBooks() {
        return vBookList;
    }

    public Book getBookByBookNameFromList(String listType, String name) {
        switch (listType) {
            case "valid":
                return getBookByBookName(vBookList, name);
            case "lent":
                return getBookByBookName(lBookList, name);
            default:
                return getBookByBookName(bookList, name);
        }
    }

    private Book getBookByBookName(List<Book> chosenList, String name) {
        return chosenList.stream()
            .filter(book -> book.getBookName().equals(name))
            .findFirst()
            .orElse(null);
    }

    public void saveBookByBookName(String listType, String name) {
        if (listType.equals("valid")) {
            vBookList.add(getBookByBookName(bookList, name));
        } else {
            lBookList.add(getBookByBookName(bookList, name));
        }
    }

    public void removeBookByBookName(String listType, String name) {
        if (listType.equals("valid")) {
            vBookList.removeIf(book -> book.getBookName().equals(name));
        } else {
            lBookList.removeIf(book -> book.getBookName().equals(name));
        }
    }
}
