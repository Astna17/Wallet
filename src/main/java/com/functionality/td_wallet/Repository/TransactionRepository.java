package com.functionality.td_wallet.Repository;

import com.functionality.td_wallet.DatabaseConnection;
import com.functionality.td_wallet.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRepository implements CrudOperation <Transaction> {

<<<<<<< HEAD
    public TransactionRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void insert(Transaction toInsert) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "INSERT INTO transaction (label, amount, date_hour, type,category) VALUES (?, ?, ?, ?, ?)";
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
    public void update(Transaction toUpdate) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "UPDATE transaction SET label=?, amount=?, date_hour=?, type=?, categorie=? WHERE id_transaction=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toUpdate.getLabel());
                statement.setDouble(2, toUpdate.getAmount());
                statement.setObject(3, toUpdate.getDateTime());
                statement.setString(4, toUpdate.getType());
                statement.setString(5, String.valueOf(toUpdate.getCategory()));
                statement.setInt(6, toUpdate.getIdTransaction());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Transaction toDelete) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "DELETE FROM transaction WHERE id_transaction=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, toDelete.getIdTransaction());

                statement.executeUpdate();
            }
        }
    }


=======
>>>>>>> b30c83e0e528c45518e255fb42aad9dd18253cab
}
