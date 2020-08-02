package com.twu.biblioteca;

import com.twu.biblioteca.entity.Biblioteca;
import com.twu.biblioteca.entity.Librarian;
import com.twu.biblioteca.exceptions.InvalidOptionException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

// 测试不要写到一起, 如果分好层, 各层写各层的,
// 层与层之间的相互调用可以Mockito(不强制)
// 整个测试最大的问题就是, 测了每一个小的方法, 唯独核心逻辑没测. 比如isTheCustomerWannaMoveOn,
public class BibliotecaTests {

    private Biblioteca biblioteca;
    private Librarian librarian;

    @Before
    public void setUp() {
        this.biblioteca = new Biblioteca();
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

    // 这个测试应该从isTheCustomerWannaMoveOn方法测
    @Test
    public void chooseInvalidOptionShouldThrowException() throws InvalidOptionException {
        exceptionRule.expect(InvalidOptionException.class);
        exceptionRule.expectMessage("Please select a valid option!");
        biblioteca.handleWrongService();
    }

    // 问题同上
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
        assertThat(biblioteca.checkOutBook("The Red and the Black"), is("Thank you! Enjoy the book"));
        assertThat(biblioteca.checkOutBook("The Red and the Black"), is("Sorry, that book is not available"));
    }

    // 第一个问题, 测试行为要单一, 如果要测Librarian行为的就应该单独起一个新的测试文件写测试. 测试也需要结构, 不是全部写在一起. 还有其他几个都一样的问题.
    // 如果要测ReturnBook, 那你就不关心checkBookState的结果, 而应该关心ReturnBook的结果.
    // 测试行为要单一, 不要把几个方法的测试都揉到一起.
    // 第二个问题, 测试一个方法, 没有Cover方法的所有分支, 也就说只有Happy path.
    // 第三个问题, 测试一个方法, 不测方法的返回结果, 那测试存在的意义是什么, 对于你要测得方法, 最直接的方式就我
    // 给一个输入, 然后期待一个输出.
    @Test
    public void bookBeReturnedShouldAppearInBookList() {
        biblioteca.checkOutBook("War and Peace");
        assertFalse(librarian.checkBookState(biblioteca.getBookList(), "War and Peace"));
        biblioteca.returnBook("War and Peace");
        assertTrue(librarian.checkBookState(biblioteca.getBookList(), "War and Peace"));
    }

    // 我这里写一个测试个returnBook, 你看一下, 理解一下测试应该是什么样的.
    // 这里原本可以直接Mock与测试方法无关方法的返回值, 但是我看你们题目不要求, 所以就采用真实行为构造测试条件了.
    @Test
    public void demoForReturnBookHappyPath() {
        // prepare 方法需要的环境
        biblioteca.checkOutBook("War and Peace");

        // mock输出结果
        String expectedResult = "Thank you for returning the book!";

        // 调用测试方法进行测试
        String actualResult = biblioteca.returnBook("War and Peace");

        // assert方法行为
        assertThat(actualResult, is(expectedResult));
        assertThat(biblioteca.getBookList().size(), is(4));
    }

    // 这个测试现在是挂的, 也验证了你代码里面的Bug, 所以是一个很好的例子, 你之前测试写的是无用的, 所以代码的问题也就发现不了.
    // 你可以先把这个测试跑一下, 看看什么问题, 然后在去修代码, 最后跑过测试, 这也就是一个TDD的过程了.
    @Test
    public void demoForReturnBookUnHappyPath() {
        // prepare 方法需要的环境
        biblioteca.checkOutBook("War and Peace");

        // mock输出结果
        String expectedResult = "Sorry, that book is not available";

        // 调用测试方法进行测试
        String actualResult = biblioteca.returnBook("The Red and the Black");

        // assert方法行为
        assertThat(actualResult, is(expectedResult));
        assertThat(biblioteca.getBookList().size(), is(4));
    }

    @Test
    public void afterReturnedMessageShouldBeSent() {
        biblioteca.checkOutBook("War and Peace");
        assertThat(biblioteca.returnBook("War and Peace"), is("Thank you for returning the book!"));
        assertThat(biblioteca.returnBook("The War and Peace"), is("This is not a valid book to return."));
    }
}
