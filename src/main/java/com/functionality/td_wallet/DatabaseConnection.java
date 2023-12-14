package com.functionality.td_wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection openConnection() throws SQLException {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            throw e;
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost/Wallet",
                "prog-admin",
                "123456"
        );
    }
}

