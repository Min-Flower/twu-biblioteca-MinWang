package com.twu.biblioteca;

import com.twu.biblioteca.data.BookData;
import com.twu.biblioteca.exceptions.InvalidProductException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BookManageService;
import org.junit.*;
import org.junit.rules.ExpectedException;

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

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void chooseInvalidOptionShouldThrowException() throws InvalidOptionException {
        exceptionRule.expect(InvalidOptionException.class);
        exceptionRule.expectMessage("Please select a valid option!");
        bookManageService.handleWrongService();
    }

    @Test
    public void chooseQuitOptionShouldExitBiblioteca() {
        assertNull(bookManageService.quit());
    }

    @Test
    public void afterCheckedOutSuccessfulMessageShouldBeSent() {
        String expectedResult = "Thank you! Enjoy the book.";
        String actualResult = bookManageService.handleBook("valid","The Red and the Black");

        assertThat(actualResult, is(expectedResult));
        assertThat(bookManageService.getValidBooks().size(), is(3));
    }

    @Test
    public void failToCheckOutInvalidExceptionShouldBeThrown() {
        try {
            bookManageService.handleBook("valid", "Red and Black");
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
        bookManageService.handleBook("valid", "War and Peace");

        String expectedResult = "Thank you for returning the book!";
        String actualResult = bookManageService.handleBook("lent","War and Peace");

        assertThat(actualResult, is(expectedResult));
        assertThat(bookManageService.getValidBooks().size(), is(4));
    }

    @Test
    public void failToReturnInvalidExceptionShouldBeThrown() {
        try {
            bookManageService.handleBook("valid", "War and Peace");
            bookManageService.handleBook("lent", "The Red and the Black");

            Assert.fail("Should throw InvalidProductException");
        } catch (InvalidProductException e) {
            assertThat(e.getMessage(), is("This is not a valid book to return."));
            assertThat(bookManageService.getValidBooks().size(), is(3));
        }
    }
}
