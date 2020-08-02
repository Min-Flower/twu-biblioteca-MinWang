package com.twu.biblioteca.entity;

import com.twu.biblioteca.exceptions.InvalidOptionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Biblioteca 作为项目的名字, 它本身不应该成为一个类或者说一个对象.
// 现在这个类其实就是图书管理系统的核心业务逻辑, 它就可以是一个Service,
// 管理系统相关的核心业务逻辑可以在这里实现.
// 类名你可以在考虑一下, 比如BookManageService
// 这里和Librarian类同样的问题, 一般Entity我们会用Pojo.
public class Biblioteca {

    // 业务逻辑不应该关心数据的存储行为, 这个数据存储行为可以自己写一个类,
    // 类中维护一个Map, 或者还用List, 模拟数据库行为.
    private List<Book> bookList = initBookList();
    private List<Book> lentBookList = new ArrayList<>();
    private Librarian librarian = new Librarian();

    public Biblioteca() {}

    // 同上
    private List<Book> initBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        bookList.add(new Book("The Red and the Black", "Stendhal", 1830));
        bookList.add(new Book("War and Peace", "Tolstoy", 1869));
        bookList.add(new Book("David Copperfield", "Charles Dickens", 1849));
        return bookList;
    }

    // 这个类如果成为业务逻辑层的内容, 那么类似于showMenu的方法就不应该出现在这里,
    // ShowMenu作为和用户交互的行为, 完全可以独立出去, 成为Controller层的内容, 或者作为Adapter存在.
    // 并且这里不要使用 \n hardcode一个换行符, 不同的系统换行符不同, 所以建议用println代替.
    public String showMenu() {
        return "====MENU====\n1. List of books\n2. Check out book\n3. Return book\n0. Quit\nChoose the service you want:";
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    // 用了stream reduce 和 Optional 的基本用法很好!
    public String displayBooks() {
        return String.format("%25s %20s %20s \n", "Book Name", "Author", "Published Year") +
            this.bookList.stream().map(book ->
            String.format("%25s %20s %20d", book.getBookName(), book.getAuthor(), book.getYearOfPublication()))
            .reduce((pre, cur) -> pre + "\n" + cur).orElse(null);
    }

    // 应该Public吗?
    public String quit() {
        return null;
    }

    // 这个方法应该Public吗?
    public void handleWrongService() throws InvalidOptionException {
        throw new InvalidOptionException("Please select a valid option!");
    }

    public String checkOutBook(String book) {
        boolean ifAvailable = librarian.checkBookState(bookList, book);
        if (ifAvailable) {
            // 这里写了一个Bug, addBook里调用了Stream产生了一个新的List, 但是返回了原来的list.
            // 返回的list又把你修改过后的lentBookList覆盖了.
            this.lentBookList = librarian.addBook(bookList, lentBookList, book);
            this.bookList = librarian.removeBook(bookList, book);
            return "Thank you! Enjoy the book";
        }
        return "Sorry, that book is not available";
    }

    // 这个方法和上面的方法像不像?
    // 遇到这种就可以想办法把不同的地方提取参数, 然后公用公共部分.
    public String returnBook(String book) {
        boolean ifLent = librarian.checkBookState(lentBookList, book);
        if (ifLent) {
            this.bookList = librarian.addBook(lentBookList, bookList, book);
            this.lentBookList = librarian.removeBook(lentBookList, book);
            return "Thank you for returning the book!";
        }
        return "This is not a valid book to return.";
    }

    // 这个核心逻辑没有测试吗?
    public boolean isTheCustomerWannaMoveOn(String choice) {
        Scanner scanner = new Scanner(System.in);
        try {
            switch (choice) {
                case "0":
                    return false;
                case "1":
                    System.out.println(displayBooks());
                    return true;
                case "2":
                    System.out.println("Please enter the book name you want:");
                    System.out.println(checkOutBook(scanner.nextLine()));
                    return true;
                case "3":
                    System.out.println("Please enter the book name you'll return:");
                    System.out.println(returnBook(scanner.nextLine()));
                    return true;
                default:
                    handleWrongService();
                    return true;
            }
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

}
