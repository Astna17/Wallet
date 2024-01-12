package com.functionality.td_wallet.Repository;

import com.functionality.td_wallet.entity.Account;
import com.functionality.td_wallet.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository implements CrudOperation<Account> {
    private final DatabaseConnection databaseConnection;

    public AccountRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save(Account toSave) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "INSERT INTO account (name, balance, date_updated, devise_id, type) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toSave.getName());
                statement.setDouble(2, toSave.getBalance());
                statement.setObject(3, toSave.getDateUpdated());
                statement.setInt(4, toSave.getDevise().getIdDevise());
                statement.setString(5, toSave.getType());

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
    @Override
    public void findAll(Account toFindAll) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "SELECT * FROM account";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, toFindAll.getIdAccount());
                statement.setString(2, toFindAll.getName());
                statement.setDouble(3, toFindAll.getBalance());
                statement.setObject(4, toFindAll.getDateUpdated());
                statement.setInt(5, toFindAll.getDevise().getIdDevise());
                statement.setString(6, toFindAll.getType());

                statement.executeUpdate();
            }
        }

    }
}
