package com.functionality.td_wallet.Repository;

<<<<<<< HEAD
import com.functionality.td_wallet.entity.Account;
import org.bibliotheque.DatabaseConnection;
=======
import com.functionality.td_wallet.DatabaseConnection;
import com.functionality.td_wallet.entity.Account;
>>>>>>> b30c83e0e528c45518e255fb42aad9dd18253cab

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository implements CrudOperation<Account> {
    private final DatabaseConnection databaseConnection;

    public AccountRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void insert(Account toInsert) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "INSERT INTO account (name, balance, date_updated, devise_id, type) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toInsert.getName());
                statement.setDouble(2, toInsert.getBalance());
                statement.setObject(3, toInsert.getDateUpdated());
                statement.setInt(4, toInsert.getDevise().getIdDevise());
                statement.setString(5, toInsert.getType());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Account toUpdate) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "UPDATE account SET name=?, balance=?, date_updated=?, devise_id=?, type=? WHERE id_account=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toUpdate.getName());
                statement.setDouble(2, toUpdate.getBalance());
                statement.setObject(3, toUpdate.getDateUpdated());
                statement.setInt(4, toUpdate.getDevise().getIdDevise());
                statement.setString(5, toUpdate.getType());
                statement.setInt(6, toUpdate.getIdAccount());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Account toDelete) throws SQLException {

    }
}
