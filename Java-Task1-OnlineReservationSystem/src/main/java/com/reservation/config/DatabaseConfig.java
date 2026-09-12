package com.reservation.config;

public final class DatabaseConfig {

    private DatabaseConfig() {
        // Prevent object creation
    }

    public static final String URL =
            "jdbc:mysql://localhost:3306/reservation_db"
                    + "?useSSL=false"
                    + "&serverTimezone=UTC";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "proud25";
}