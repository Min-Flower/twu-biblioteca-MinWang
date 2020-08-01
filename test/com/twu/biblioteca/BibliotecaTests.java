package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BibliotecaTests {

    @Test
    public void customerEnterBibliotecaShouldBeWelcomed() {
        Biblioteca biblioteca = new Biblioteca();
        String WELCOME_MASSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertThat(biblioteca.welcome(), is(WELCOME_MASSAGE));
    }

    @Test
    public void afterWelcomeShouldReturnMenu() {
        Biblioteca biblioteca = new Biblioteca();
        assertThat(biblioteca.showMenu(), is("====MENU====\n1. List of books\nChoose the service you want:"));
    }

    @Test
    public void chooseDisplayBooksShouldReturnBookList() {
        Biblioteca biblioteca = new Biblioteca();
        String existBookList = String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year")
            + String.format("%25s %20s %20d","Pride and Prejudice", "Jane Austen", 1813) + "\n"
            + String.format("%25s %20s %20d", "The Red and the Black", "Stendhal", 1830) + "\n"
            + String.format("%25s %20s %20d", "War and Peace", "Tolstoy", 1869) + "\n"
            + String.format("%25s %20s %20d", "David Copperfield", "Charles Dickens", 1849);
        assertThat(biblioteca.chooseService("1"), is(existBookList));
    }
}