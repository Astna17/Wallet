package org.bibliotheque.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Account {
    private final int idAccount;
    private final String name;
    private double balance;
    private final LocalDateTime dateUpdated;
    private final List<Transaction> transactions;
    private final Devise devise;
    private final String type;

    public Account(int idAccount, String name, double balance,
                   LocalDateTime dateUpdated, List<Transaction> transactions,
                   Devise devise, String type) {
        this.idAccount = idAccount;
        this.name = name;
        this.balance = balance;
        this.dateUpdated = dateUpdated;
        this.transactions = transactions;
        this.devise = devise;
        this.type = type;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public Devise getDevise() {
        return devise;
    }


    public String getType() {
        return type;
    }

    public void setTransactions() {
    }

    private static int transactionIdCounter = 0;
    // Méthode de génération d'identifiant pour Transaction
    private int generateTransactionId() {
        return ++transactionIdCounter;
    }


    public double getBalanceAtDateTime(LocalDateTime dateTime) {
        // Tri des transactions
        transactions.sort(Comparator.comparing(Transaction::getDateTime));

        double balance = 0;

        for (Transaction transaction : transactions) {
            LocalDateTime transactionDateTime = transaction.getDateTime();

            if (!transactionDateTime.isAfter(dateTime)) {
                if (transaction.getType().equals("credit")) {
                    balance += transaction.getAmount();
                } else {
                    balance -= transaction.getAmount();
                }
            } else {
                break;
            }
        }

        return balance;
    }

    public List<BalanceHistoryEntry> getBalanceHistoryInInterval(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        transactions.sort(Comparator.comparing(Transaction::getDateTime));

        List<BalanceHistoryEntry> balanceHistory = new ArrayList<>();
        double currentBalance = 0;

        for (Transaction transaction : transactions) {
            LocalDateTime transactionDateTime = transaction.getDateTime();

            if (!transactionDateTime.isBefore(startDateTime) && !transactionDateTime.isAfter(endDateTime)) {
                if (transaction.getType().equals("credit")) {
                    currentBalance += transaction.getAmount();
                } else {
                    currentBalance -= transaction.getAmount();
                }

                balanceHistory.add(new BalanceHistoryEntry(transactionDateTime, currentBalance));
            } else if (transactionDateTime.isAfter(endDateTime)) {

                break;
            }
        }

        return balanceHistory;
    }


    // Classe pour représenter une entrée d'historique du solde
    public static class BalanceHistoryEntry {
        private final LocalDateTime dateTime;
        private final double balance;

        public BalanceHistoryEntry(LocalDateTime dateTime, double balance) {
            this.dateTime = dateTime;
            this.balance = balance;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public double getBalance() {
            return balance;
        }
    }

    public void transferMoney(Account destinationAccount, double amount, LocalDateTime transferDate) {
        validateTransfer(destinationAccount, amount);

        Transaction debitTransaction = new Transaction();
        Transaction creditTransaction = new Transaction();

        this.transactions.add(debitTransaction);
        destinationAccount.transactions.add(creditTransaction);

        updateBalancesOnTransfer(amount, destinationAccount);

        TransferHistory transferHistory = new TransferHistory
                (debitTransaction.getIdTransaction(), creditTransaction.getIdTransaction(), transferDate);
    }

    private void validateTransfer(Account destinationAccount, double amount) {
        if (Objects.equals(this.idAccount, destinationAccount.getIdAccount())) {
            throw new IllegalArgumentException("Impossible de transférer de l'argent vers le même compte.");
        }

        if (!this.devise.equals(destinationAccount.getDevise())) {
            throw new IllegalArgumentException("Les comptes n'ont pas la même devise.");
        }

        if (this.balance < amount) {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le transfert.");
        }
    }

    private void updateBalancesOnTransfer(double amount, Account destinationAccount) {
        this.balance -= amount;
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
    }

    private void setBalance(double Solde) {
    }




    public double getAriaryBalanceWithExchangeRate(LocalDateTime date) {
        double ariaryBalance = 0;

        // Tri des transactions
        transactions.sort(Comparator.comparing(Transaction::getDateTime));

        for (Transaction transaction : transactions) {
            LocalDateTime transactionDateTime = transaction.getDateTime();

            if (transactionDateTime.isAfter(date)) {
                break;  // Sortir si la transaction est après la date donnée
            }

            // Obtenir le taux de change pour la devise source vers Ariary à la date de la transaction
            double exchangeRate = getExchangeRate(transaction.getDevise().getId(), transactionDateTime);

            // Mettre à jour le solde en Ariary
            if (transaction.getType().equals("credit")) {
                ariaryBalance += transaction.getAmount() * exchangeRate;
            } else {
                ariaryBalance -= transaction.getAmount() * exchangeRate;
            }
        }

        return ariaryBalance;
    }

    private double getExchangeRate(String sourceCurrency, LocalDateTime date) {
        List<CurrencyValue> currencyValues = currencyValueRepository.findBySourceCurrencyIdAndDestinationCurrencyIdAndEffectiveDateBeforeOrderByEffectiveDateDesc(
                sourceCurrency, "Ariary", date.toLocalDate());

        if (!currencyValues.isEmpty()) {
            return currencyValues.get(0).getExchangeRate();
        }

        throw new IllegalStateException("Le taux de change n'a pas été trouvé pour la devise spécifiée à la date donnée.");
    }

}