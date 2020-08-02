package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.InvalidBookException;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BookManageService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    }

    @Test
    public void customerEnterBibliotecaShouldBeWelcomed() {
        String WELCOME_MASSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertThat(bookManageService.welcome(), is(WELCOME_MASSAGE));
    }

    @Test
    public void chooseDisplayBooksShouldReturnBookList() {
        List<String> existBookList = new ArrayList<>();
        existBookList.add(String.format("%25s %20s %20s", "Book Name", "Author", "Published Year"));
        bookManageService.getValidBooks()
            .forEach(book -> existBookList.add(String.format(
                "%25s %20s %20s", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
            );
        assertThat(bookManageService.displayBooks(), is(String.join("\n", existBookList)));
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
        assertThat(bookManageService.handleBook("valid","The Red and the Black"), is("Thank you! Enjoy the book."));
    }

    @Test
    public void failToCheckOutInvalidExceptionShouldBeThrown() {
        exceptionRule.expect(InvalidBookException.class);
        exceptionRule.expectMessage("Sorry, that book is not available.");
        bookManageService.handleBook("valid","Red and Black");
    }

    @Test
    public void bookBeReturnedShouldAppearInBookList() {
        bookManageService.handleBook("valid", "War and Peace");
        String expectedResult = "Thank you for returning the book!";
        String actualResult = bookManageService.handleBook("lent","War and Peace");

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void afterReturnedMessageShouldBeSent() {
        bookManageService.handleBook("valid", "War and Peace");

        assertThat(bookManageService.handleBook("lent", "War and Peace")
            , is("Thank you for returning the book!"));
    }

    @Test
    public void failToReturnInvalidExceptionShouldBeThrown() {
        exceptionRule.expect(InvalidBookException.class);
        exceptionRule.expectMessage("This is not a valid book to return.");
        bookManageService.handleBook("lent", "Red and Black");
    }
}
