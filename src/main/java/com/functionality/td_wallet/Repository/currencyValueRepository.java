package com.functionality.td_wallet.Repository;

import com.functionality.td_wallet.DatabaseConnection;
import com.functionality.td_wallet.entity.CurrencyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class currencyValueRepository {
    private DatabaseConnection databaseConnection;

    public void CurrencyValueRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public currencyValueRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int getMontantEuro(String date) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String query = "SELECT Montant FROM CurrencyValue WHERE Date_effet <= ? ORDER BY Date_effet DESC LIMIT 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, date);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return 1 * resultSet.getInt("Montant");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public void updateTauxChange(String date, int nouveauTaux) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String query = "UPDATE CurrencyValue SET TauxChange = ? WHERE Date_effet = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, nouveauTaux);
                preparedStatement.setString(2, date);
                preparedStatement.executeUpdate();
            }
        }
    }

    public Optional<CurrencyValue> findFirstByDateEffetBeforeOrderByDateEffetDesc(Date date) {
        return null;
    }
}
