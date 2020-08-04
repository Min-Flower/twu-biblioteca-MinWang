package com.twu.biblioteca;

import com.twu.biblioteca.controller.ManageController;
import com.twu.biblioteca.data.UserData;
import com.twu.biblioteca.exceptions.FailToVerifyException;
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
        String expectResult = "====MENU====\n1. List of books\n2. Check out book\n3. Return book\n4. List of movies\n" +
            "5. Check out movie\n0. Quit\nChoose the service you want:\n";
        manageController.showMenu();

        assertEquals(expectResult, outContent.toString());
    }

    @Test
    public void loginAsLibrarianShouldBeAbleToCheckBorrowingRecord() {
        manageController.checkOutBook("1", "The Red and the Black");
        manageController.checkOutBook("2", "War and Peace");

        String expectedResult = "1. The Red and the Black, User: 1\n2. War and Peace, User: 2\n";
        String actualResult = manageController.checkBorrowingRecord();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void noRecordAboutBorrowingShouldBeNoticedToLibrarian() {
        String expectedResult = "No record now.";
        String actualResult = manageController.checkBorrowingRecord();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void loginAsCustomerShouldBeVerified() {
        String username = UserData.userList.get(0).getName();
        String password = UserData.userList.get(0).getPassword();

        assertNotNull(manageController.getUserInfo(username, password));

        try {
            manageController.getUserInfo(username, password + ".");

            Assert.fail("Should throw FailToVerifyException");

        } catch (FailToVerifyException e) {
            String expectedResult = "Please enter the right username and password!";
            String actualResult = e.getMessage();

            assertThat(actualResult, is(expectedResult));
        }
    }

    @Test
    public void afterLoginCustomerShouldBeAbleToSeeHisInfoExceptPassword() {
        String username = "Min";
        String password = "123456";

        String expectedResult = "{id: 1, name: Min, email: min@xxx.com, phone: 8866209}";
        String actualResult = manageController.getUserInfo(username, password).toString();

        assertEquals(expectedResult, actualResult);
    }

}