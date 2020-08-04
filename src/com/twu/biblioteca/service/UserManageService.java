package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.UserManageRepository;

public class UserManageService {

    private UserManageRepository userManageRepository = new UserManageRepository();

    public User getUserInfo(String username, String password) {
        return userManageRepository.getUserByUsernameAndPassword(username, password);
    }
}
