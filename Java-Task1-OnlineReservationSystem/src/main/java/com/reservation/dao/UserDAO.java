package com.reservation.dao;

import com.reservation.model.User;
import com.reservation.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String FIND_USER_BY_CREDENTIALS =
            "SELECT user_id, username, password, full_name " +
                    "FROM users " +
                    "WHERE username = ? AND password = ?";

    public User authenticate(String username, String password) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_USER_BY_CREDENTIALS)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name")
                    );
                }
            }

        } catch (SQLException exception) {

            System.err.println("Authentication error: "
                    + exception.getMessage());
        }

        return null;
    }

    public boolean usernameExists(String username) {

        String sql = "SELECT user_id FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException exception) {

            System.err.println("Username check error: "
                    + exception.getMessage());

            return false;
        }
    }
}