CREATE FUNCTION sum_between_dates(
    compte_id INT,
    date_debut TIMESTAMP,
    date_fin TIMESTAMP
) RETURNS NUMERIC AS $$
DECLARE
solde NUMERIC := 0;
BEGIN

SELECT COALESCE(SUM(montant), 0) INTO solde FROM transactions
WHERE compte_id = sum_between_dates.compte_id
  AND date_transaction BETWEEN date_debut AND date_fin
  AND montant > 0;

SELECT COALESCE(SUM(montant), 0) INTO solde FROM transactions
WHERE compte_id = sum_between_dates.compte_id
  AND date_transaction BETWEEN date_debut AND date_fin
  AND montant < 0;

RETURN solde;
END;
$$ LANGUAGE plpgsql;
