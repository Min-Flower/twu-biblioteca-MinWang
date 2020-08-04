package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ManageControllerTests {

    private ManageController manageController = new ManageController();

    @Test
    public void customerEnterBibliotecaShouldBeWelcomed() {
        String expectedResult = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        String actualResult = manageController.welcome();

        assertThat(actualResult, is(expectedResult));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void chooseInvalidOptionShouldThrowException() throws InvalidOptionException {
        exceptionRule.expect(InvalidOptionException.class);
        exceptionRule.expectMessage("Please select a valid option!");
        manageController.handleWrongService();
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void userFromClientShouldBeShownTheMenu() {
        String expectResult = "====MENU====\n1. List of books\n2. Check out book\n3. Return book\n4. List of Movies\n" +
            "5. Check out movie\n0. Quit\nChoose the service you want:\n";
        manageController.showMenu();

        assertEquals(expectResult, outContent.toString());
    }

}