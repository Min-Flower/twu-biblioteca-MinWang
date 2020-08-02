package com.twu.biblioteca;

import com.twu.biblioteca.entity.Librarian;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaTests {

    private BibliotecaService biblioteca;
    private Librarian librarian;

    @Before
    public void setUp() {
        this.biblioteca = new BibliotecaService();
        this.librarian = new Librarian();
    }

    @After
    public void tearDown() {
        this.biblioteca = null;
        this.librarian = null;
    }

    @Test
    public void customerEnterBibliotecaShouldBeWelcomed() {
        String WELCOME_MASSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertThat(biblioteca.welcome(), is(WELCOME_MASSAGE));
    }

    @Test
    public void afterWelcomeShouldReturnMenu() {
        assertThat(biblioteca.showMenu(),
            is("====MENU====\n1. List of books\n2. Check out book\n3. Return book\n0. Quit\nChoose the service you want:"));
    }

    @Test
    public void chooseDisplayBooksShouldReturnBookList() {
        String existBookList = String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year")
            + String.format("%25s %20s %20d","Pride and Prejudice", "Jane Austen", 1813) + "\n"
            + String.format("%25s %20s %20d", "The Red and the Black", "Stendhal", 1830) + "\n"
            + String.format("%25s %20s %20d", "War and Peace", "Tolstoy", 1869) + "\n"
            + String.format("%25s %20s %20d", "David Copperfield", "Charles Dickens", 1849);
        assertThat(biblioteca.displayBooks(), is(existBookList));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void chooseInvalidOptionShouldThrowException() throws InvalidOptionException {
        exceptionRule.expect(InvalidOptionException.class);
        exceptionRule.expectMessage("Please select a valid option!");
        biblioteca.handleWrongService();
    }

    @Test
    public void chooseQuitOptionShouldExitBiblioteca() {
        assertNull(biblioteca.quit());
    }

    @Test
    public void checkOutABookShouldReturnIfAvailable() {
        assertTrue(librarian.checkBookState(biblioteca.getBookList(), "The Red and the Black"));
        assertFalse(librarian.checkBookState(biblioteca.getBookList(), "Million Pound"));
    }

    @Test
    public void bookBeCheckedShouldNotAppearInBookList() {
        biblioteca.checkOutBook("The Red and the Black");
        assertFalse(librarian.checkBookState(biblioteca.getBookList(), "The Red and the Black"));
    }

    @Test
    public void afterCheckedOutMessageShouldBeSent() {
        assertThat(biblioteca.checkOutBook("The Red and the Black"), is("Thank you! Enjoy the book."));
        assertThat(biblioteca.checkOutBook("The Red and the Black"), is("Sorry, that book is not available."));
    }

    @Test
    public void bookBeReturnedShouldAppearInBookList() {
        biblioteca.checkOutBook("War and Peace");
        assertFalse(librarian.checkBookState(biblioteca.getBookList(), "War and Peace"));
        biblioteca.returnBook("War and Peace");
        assertTrue(librarian.checkBookState(biblioteca.getBookList(), "War and Peace"));
    }

    @Test
    public void afterReturnedMessageShouldBeSent() {
        biblioteca.checkOutBook("War and Peace");
        assertThat(biblioteca.returnBook("War and Peace"), is("Thank you for returning the book!"));
        assertThat(biblioteca.returnBook("The War and Peace"), is("This is not a valid book to return."));
    }
}
