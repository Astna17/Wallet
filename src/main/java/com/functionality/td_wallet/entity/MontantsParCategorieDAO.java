package com.functionality.td_wallet.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;

public class MontantsParCategorieDAO {

    private static final String JDBC_URL = "jdbc:postgresql://localhost/votre_base_de_donnees";
    private static final String JDBC_USER = "votre_utilisateur";
    private static final String JDBC_PASSWORD = "votre_mot_de_passe";

    public static MontantsParCategorie sommeMontantsParCategorieEntreDates(
            int compteId, Timestamp dateDebut, Timestamp dateFin) {

        MontantsParCategorie montantsParCategorie = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
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
            e.printStackTrace();
        }

        return montantsParCategorie;
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        Timestamp dateDebut = Timestamp.valueOf("2023-12-01 00:00:00");
        Timestamp dateFin = Timestamp.valueOf("2023-12-02 23:59:59");

        MontantsParCategorie resultat = sommeMontantsParCategorieEntreDates(1, dateDebut, dateFin);

        if (resultat != null) {
            System.out.println("Restaurant : " + resultat.getRestaurant());
            System.out.println("Salaire : " + resultat.getSalaire());
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
    }
}
