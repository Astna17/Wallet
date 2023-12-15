package com.functionality.td_wallet.entity;

import com.functionality.td_wallet.Service.ExchangeRate;

import java.time.LocalDateTime;
import java.util.List;

public class Account {
    private int idAccount;
    private String name;
    private double balance;
    private LocalDateTime dateUpdated;
    private List<Transaction> transactions;
    private Devise devise;
    private String type;

    public Account(int idAccount, String name, double balance, LocalDateTime dateUpdated, List<Transaction> transactions, Devise devise, String type) {
        this.idAccount = idAccount;
        this.name = name;
        this.balance = balance;
        this.dateUpdated = dateUpdated;
        this.transactions = transactions;
        this.devise = devise;
        this.type = type;
    }

    public Account(int idAccount, String name, double balance, Devise euro, String bank) {

    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void performTransaction(String label, double amount, String transactionType) {
        int transactionId = transactions.size() + 1;
        LocalDateTime dateTimeTransaction = LocalDateTime.now();

        if (transactionType.equals("debit")) {
            if (!type.equals("Bank") && (balance - amount) < 0) {
                System.out.println("Insufficient balance to perform the transaction.");
                return;
            }
        }

        Transaction newTransaction = new Transaction(transactionId, label, amount, dateTimeTransaction, transactionType);
        transactions.add(newTransaction);

        // Update the balance
        if (transactionType.equals("debit")) {
            balance -= amount;
        } else if (transactionType.equals("credit")) {
            balance += amount;
        }
    }


    public void transferMoney(Account destinationAccount, double amount, LocalDateTime exchangeRateDate) {
        if (this == destinationAccount) {
            System.out.println("Error: Cannot transfer money to the same account.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Error: Transfer amount must be greater than zero.");
            return;
        }

        if (this.balance < amount) {
            System.out.println("Error: Insufficient funds for the transfer.");
            return;
        }
        Devise sourceCurrency = this.devise;
        Devise targetCurrency = destinationAccount.devise;

        double exchangeRate = ExchangeRate.getExchangeRate(sourceCurrency.getCode(),  targetCurrency.getCode(), dateUpdated);

        // Convert the amount to the target currency
        double convertedAmount = amount * exchangeRate;

        // Effectuer le transfert depuis le compte source
        this.performTransaction("Transfer to " + destinationAccount.getName(), amount, "debit");

        // Effectuer le transfert vers le compte de destination
        destinationAccount.performTransaction("Transfer from " + this.getName(), amount, "credit");

        System.out.println("Transfer successful. New balance for " + this.getName() + ": " + this.getBalance());
        System.out.println("New balance for " + destinationAccount.getName() + ": " + destinationAccount.getBalance());
    }

}
