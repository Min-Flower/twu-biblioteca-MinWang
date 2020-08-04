package com.twu.biblioteca.repository;

import com.twu.biblioteca.data.UserData;
import com.twu.biblioteca.entity.User;

public class UserManageRepository {

    public User getUserByUsernameAndPassword(String username, String password) {
        return UserData.userList.stream()
            .filter(user -> user.getName().equals(username)
                && user.getPassword().equals(password))
            .findFirst().orElse(null);
    }
}
