package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;
import org.junit.*;

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

    @Test
    public void ifCustomerChooseZeroShouldQuitAndReturnFalse() {
        boolean expectedResult = manageController.isTheCustomerWannaMoveOn("0");

        assertFalse(expectedResult);
    }

    @Test
    public void ifCustomerChoose1To3ShouldMoveOnAndReturnTrue() {
        boolean expectedResult = manageController.isTheCustomerWannaMoveOn("1");
        assertTrue(expectedResult);
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
    public void ifChooseOtherThan0to3ShouldBeNotified() {
        String expectedResult = "Please select a valid option!\n";
        manageController.isTheCustomerWannaMoveOn("4");

        assertEquals(expectedResult, outContent.toString());
    }

}