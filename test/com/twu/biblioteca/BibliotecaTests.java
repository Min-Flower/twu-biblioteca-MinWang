package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BibliotecaTests {

    private Biblioteca biblioteca;

    @Before
    public void setUp() {
        this.biblioteca = new Biblioteca();
    }

    @After
    public void tearDown() {
        this.biblioteca = null;
    }

    @Test
    public void customerEnterBibliotecaShouldBeWelcomed() {
        String WELCOME_MASSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertThat(biblioteca.welcome(), is(WELCOME_MASSAGE));
    }

    @Test
    public void afterWelcomeShouldReturnMenu() {
        assertThat(biblioteca.showMenu(), is("====MENU====\n1. List of books\n0. Quit\nChoose the service you want:"));
    }

    @Test
    public void chooseDisplayBooksShouldReturnBookList() {
        String existBookList = String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year")
            + String.format("%25s %20s %20d","Pride and Prejudice", "Jane Austen", 1813) + "\n"
            + String.format("%25s %20s %20d", "The Red and the Black", "Stendhal", 1830) + "\n"
            + String.format("%25s %20s %20d", "War and Peace", "Tolstoy", 1869) + "\n"
            + String.format("%25s %20s %20d", "David Copperfield", "Charles Dickens", 1849);
        assertThat(biblioteca.chooseService("1"), is(existBookList));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void chooseInvalidOptionShouldThrowException() throws InvalidOptionException {
        exceptionRule.expect(InvalidOptionException.class);
        exceptionRule.expectMessage("Please select a valid option!");
        biblioteca.chooseService("12");
    }

    @Test
    public void chooseQuitOptionShouldExitBiblioteca() {
        assertNull(biblioteca.chooseService("0"));
    }
}
