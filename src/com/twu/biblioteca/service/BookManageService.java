package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.exceptions.InvalidProductException;
import com.twu.biblioteca.repository.BookManageRepository;

import java.util.List;

public class BookManageService {

    BookManageRepository bRepo = new BookManageRepository();

    public List<Book> getValidBooks() {
        return bRepo.getBooksByType("valid");
    }

    public String displayBooks() {
        return String.format("%25s %20s %20s\n", "Book Name", "Author", "Published Year") +
            getValidBooks().stream().map(book ->
                String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
                .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    public String handleBook(String userId, String bookType, String book) {
        if (bRepo.handleBook(userId, bookType, book)) {
            return bookType.equals("valid") ? "Thank you! Enjoy the book." : "Thank you for returning the book!";
        } else {
            throw new InvalidProductException(bookType.equals("valid") ?
                "Sorry, that book is not available." : "This is not a valid book to return.");
        }
    }

    public String checkBorrowingRecord() {
        StringBuilder record = new StringBuilder();
        List<Book> bookList = bRepo.getBooksByType("lent");
        for (int i = 0; i < bookList.size(); i++) {
            record.append(i + 1).append(". ").append(bookList.get(i).getBookName()).append(", User: ").append(bookList.get(i).getUserId()).append("\n");
        }
        return record.toString().equals("") ? "No record now." : record.toString();
    }
}
