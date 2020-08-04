package com.twu.biblioteca.data;

import com.twu.biblioteca.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    public static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("1", "Min", "min@xxx.com", "8866209", "123456"));
        userList.add(new User("2", "Grace", "grace@xxx.com", "8866208", "123456"));
        userList.add(new User("3", "Flower", "flower@xxx.com", "8866206", "123456"));
    }

}
