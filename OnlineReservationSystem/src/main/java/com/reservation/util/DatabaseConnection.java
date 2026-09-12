package com.reservation.util;

import com.reservation.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
        // Prevent object creation
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.URL,
                DatabaseConfig.USERNAME,
                DatabaseConfig.PASSWORD
        );
    }

    public static void testConnection() {

        try (Connection connection = getConnection()) {

            System.out.println("======================================");
            System.out.println(" DATABASE CONNECTION SUCCESSFUL");
            System.out.println(" Database: " + connection.getCatalog());
            System.out.println("======================================");

        } catch (SQLException exception) {

            System.err.println("======================================");
            System.err.println(" DATABASE CONNECTION FAILED");
            System.err.println(" Error: " + exception.getMessage());
            System.err.println("======================================");
        }
    }
}