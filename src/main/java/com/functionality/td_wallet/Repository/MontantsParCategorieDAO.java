package com.functionality.td_wallet.Repository;

import com.functionality.td_wallet.DatabaseConnection;
import com.functionality.td_wallet.entity.MontantsParCategorie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class MontantsParCategorieDAO {

    private static  DatabaseConnection databaseConnection ;

    public MontantsParCategorieDAO (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public static MontantsParCategorie sommeMontantsParCategorieEntreDates(
            int compteId, Timestamp dateDebut, Timestamp dateFin) {

        MontantsParCategorie montantsParCategorie = null;

        try (Connection connection = databaseConnection.openConnection()) {
            String sql = "SELECT " +
                    "COALESCE(SUM(CASE WHEN t.categorie = 'Restaurant' THEN t.montant ELSE 0 END), 0) AS restaurant, " +
                    "COALESCE(SUM(CASE WHEN t.categorie = 'Salaire' THEN t.montant ELSE 0 END), 0) AS salaire " +
                    "FROM categories c " +
                    "LEFT JOIN transactions t ON c.categorie_id = t.categorie_id " +
                    "AND t.compte_id = ? " +
                    "AND t.date_transaction BETWEEN ? AND ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, compteId);
                preparedStatement.setTimestamp(2, dateDebut);
                preparedStatement.setTimestamp(3, dateFin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        BigDecimal restaurant = resultSet.getBigDecimal("restaurant");
                        BigDecimal salaire = resultSet.getBigDecimal("salaire");
                        montantsParCategorie = new MontantsParCategorie(restaurant, salaire);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return montantsParCategorie;
        }
}