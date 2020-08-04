package com.twu.biblioteca;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.exceptions.InvalidProductException;
import com.twu.biblioteca.service.BookManageService;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookManageServiceTests {

    private BookManageService bookManageService;

    @Before
    public void setUp() {
        this.bookManageService = new BookManageService();
    }

    @After
    public void tearDown() {
        this.bookManageService = null;
        BookData.initDB();
    }

    @Test
    public void chooseDisplayBooksShouldReturnBookList() {
        List<String> existBookList = new ArrayList<>();
        existBookList.add(String.format("%25s %20s %20s", "Book Name", "Author", "Published Year"));
        bookManageService.getValidBooks()
            .forEach(book -> existBookList.add(String.format(
                "%25s %20s %20s", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
            );

        String expectedResult = String.join("\n", existBookList);
        String actualResult = bookManageService.displayBooks();

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void afterCheckedOutSuccessfulMessageShouldBeSent() {
        String expectedResult = "Thank you! Enjoy the book.";
        String actualResult = bookManageService.handleBook("1", "valid","The Red and the Black");

        assertThat(actualResult, is(expectedResult));
        assertThat(bookManageService.getValidBooks().size(), is(3));
    }

    @Test
    public void failToCheckOutInvalidExceptionShouldBeThrown() {
        try {
            bookManageService.handleBook("1","valid", "Red and Black");
            Assert.fail("Should throw InvalidProductException");
        } catch (InvalidProductException e) {
            String expectedResult = "Sorry, that book is not available.";
            String actualResult = e.getMessage();
            assertThat(actualResult, is(expectedResult));
            assertThat(bookManageService.getValidBooks().size(), is(4));
        }
    }

    @Test
    public void bookBeReturnedShouldAppearInBookList() {
        bookManageService.handleBook("1", "valid", "War and Peace");

        String expectedResult = "Thank you for returning the book!";
        String actualResult = bookManageService.handleBook("1", "lent","War and Peace");

        assertThat(actualResult, is(expectedResult));
        assertThat(bookManageService.getValidBooks().size(), is(4));
    }

    @Test
    public void failToReturnInvalidExceptionShouldBeThrown() {
        try {
            bookManageService.handleBook("1", "valid", "War and Peace");
            bookManageService.handleBook("1", "lent", "The Red and the Black");

            Assert.fail("Should throw InvalidProductException");
        } catch (InvalidProductException e) {
            assertThat(e.getMessage(), is("This is not a valid book to return."));
            assertThat(bookManageService.getValidBooks().size(), is(3));
        }
    }
}
