package com.twu.biblioteca.data;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookData {

    public static List<Book> bookList = new ArrayList<>();
    public static List<Book> lentBookList = new ArrayList<>();
    public static List<Book> validBookList = new ArrayList<>();

    static {
        initDB();
    }

    public static void initDB() {
        bookList = new ArrayList<>();
        lentBookList = new ArrayList<>();
        validBookList = new ArrayList<>();
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        bookList.add(new Book("The Red and the Black", "Stendhal", 1830));
        bookList.add(new Book("War and Peace", "Tolstoy", 1869));
        bookList.add(new Book("David Copperfield", "Charles Dickens", 1849));
        validBookList.addAll(bookList);
    }
}
