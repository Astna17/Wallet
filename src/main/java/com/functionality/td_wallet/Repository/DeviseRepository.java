package com.functionality.td_wallet.Repository;
import com.functionality.td_wallet.entity.Devise;
import com.functionality.td_wallet.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeviseRepository implements CrudOperation <Devise>{
    private final DatabaseConnection databaseConnection;

    public DeviseRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save (Devise toSave) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "INSERT INTO devise (devise_name, code) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toSave.getDeviseName());
                statement.setString(2, toSave.getCode());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Devise toUpdate) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "UPDATE devise SET devise_name=?, code=? WHERE id_devise=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toUpdate.getDeviseName());
                statement.setString(2, toUpdate.getCode());
                statement.setInt(3, toUpdate.getIdDevise());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Devise toDelete) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "DELETE FROM devise WHERE id_devise=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, toDelete.getIdDevise());

                statement.executeUpdate();
            }
        }
    }
    @Override
    public void findAll(Devise toFindAll) throws SQLException {
        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "SELECT * FROM Devise";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, toFindAll.getDeviseName());
                statement.setString(2, toFindAll.getCode());
                statement.setInt(3, toFindAll.getIdDevise());

                statement.executeUpdate();
            }
        }
    }
}
