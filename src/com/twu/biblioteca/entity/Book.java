package com.twu.biblioteca.entity;

public class Book {

    private String bookName;
    private String author;
    private int yearOfPublication;
    private String state = "valid";

    public Book() {}

    public Book(String bookName, String author, int yearOfPublication) {
        this.bookName = bookName;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
