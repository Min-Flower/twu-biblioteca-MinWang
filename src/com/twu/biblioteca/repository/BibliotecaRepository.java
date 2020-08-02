package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaRepository {

    private List<Book> bookList = BookData.bookList;
    private List<Book> lentBookList = new ArrayList<>();


    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getLentBookList() {
        return lentBookList;
    }

    public void setLentBookList(List<Book> lentBookList) {
        this.lentBookList = lentBookList;
    }
}
