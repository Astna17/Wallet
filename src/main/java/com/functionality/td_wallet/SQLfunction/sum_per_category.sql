CREATE FUNCTION sum_per_category(
    compte_id INT,
    date_debut TIMESTAMP,
    date_fin TIMESTAMP
) RETURNS TABLE (restaurant NUMERIC, salaire NUMERIC) AS $$
BEGIN
RETURN QUERY
SELECT
    COALESCE(SUM(CASE WHEN t.categorie = 'Restaurant' THEN t.montant ELSE 0 END), 0) AS restaurant,
    COALESCE(SUM(CASE WHEN t.categorie = 'Salaire' THEN t.montant ELSE 0 END), 0) AS salaire
FROM categories c
         LEFT JOIN transactions t ON c.categorie_id = t.categorie_id
    AND t.compte_id = sum_per_category.compte_id
    AND t.date_transaction BETWEEN date_debut AND date_fin;
END;
$$ LANGUAGE plpgsql;
