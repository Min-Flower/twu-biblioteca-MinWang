package com.twu.biblioteca.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Biblioteca {

    private List<Book> bookList = new ArrayList<>();

    public Biblioteca() {}

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    private void setBookList() {
        String[] existBooks = {"Pride and Prejudice", "The Red and the Black", "War and Peace", "David Copperfield"};
        this.bookList = Stream.of(existBooks)
            .map(Book::new)
            .collect(Collectors.toList());
    }

    public String displayBooks() {
        setBookList();
        return IntStream.range(0, bookList.size())
            .mapToObj(index -> index + 1 + ". " + bookList.get(index).getBookName())
            .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }
}
