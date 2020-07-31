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
}
