package com.functionality.td_wallet.Repository;

import com.functionality.td_wallet.DatabaseConnection;
import com.functionality.td_wallet.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepository implements CrudOperation <Transaction> {
    private final DatabaseConnection databaseConnection;

    public TransactionRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void insert(Transaction toInsert) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "INSERT INTO transaction (label, amount, date_hour, type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toInsert.getLabel());
                statement.setDouble(2, toInsert.getAmount());
                statement.setObject(3, toInsert.getDateTime());
                statement.setString(4, toInsert.getType());
                statement.setString(5, String.valueOf(toInsert.getCategory()));
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(com.functionality.td_wallet.entity.Transaction toUpdate) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "UPDATE transaction SET label=?, amount=?, date_hour=?, type=? WHERE id_transaction=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, toUpdate.getLabel());
                    statement.setDouble(2, toUpdate.getAmount());
                    statement.setObject(3, toUpdate.getDateTime());
                    statement.setString(4, toUpdate.getType());
                    statement.setString(5, String.valueOf(toUpdate.getCategory()));
                    statement.executeUpdate();

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(com.functionality.td_wallet.entity.Transaction toDelete) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "DELETE FROM transaction WHERE id_transaction=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, toDelete.getIdTransaction());

                statement.executeUpdate();
            }
        }
    }
}
