package com.functionality.td_wallet.entity;
import com.functionality.td_wallet.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class TransactionCrudOperations {

    private final DatabaseConnection databaseConnection;
    public TransactionCrudOperations(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public static Map<String, BigDecimal> sumAmountsByCategoryBetweenDates(int accountId, Timestamp dateDebut, Timestamp dateFin) {
        Map<String, BigDecimal> result = new HashMap<>();

        try (Connection connection = DatabaseConnection.openconnection) {
            String sql = "SELECT category, COALESCE(SUM(amount), 0) AS total_amount " +
                    "FROM transactions " +
                    "WHERE account_id = ? AND transaction_date BETWEEN ? AND ? " +
                    "GROUP BY category";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, accountId);
                preparedStatement.setTimestamp(2, dateDebut);
                preparedStatement.setTimestamp(3, dateFin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String category = resultSet.getString("category");
                        BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                        result.put(category, totalAmount);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


}

