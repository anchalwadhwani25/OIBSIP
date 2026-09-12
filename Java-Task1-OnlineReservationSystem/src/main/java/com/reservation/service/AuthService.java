package com.reservation.service;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) {

        if (username == null || username.isBlank()) {
            return null;
        }

        if (password == null || password.isBlank()) {
            return null;
        }

        return userDAO.authenticate(
                username.trim(),
                password
        );
    }

    public boolean usernameExists(String username) {

        if (username == null || username.isBlank()) {
            return false;
        }

        return userDAO.usernameExists(username.trim());
    }
}