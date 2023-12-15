package com.functionality.td_wallet.entity;

import java.time.LocalDateTime;

public class Transaction {
    private int idTransaction;
    private String label;
    private double amount;
    private LocalDateTime dateTime;
    private String type; // "debit" pour les sorties, "credit" pour les entrées
    private Category category;

    public Transaction(int idTransaction, String label, double amount, LocalDateTime dateTime, String type, Category category) {
        this.idTransaction = idTransaction;
        this.label = label;
        this.amount = amount;
        this.dateTime = dateTime;
        this.type = type;
        this.category = category;
    }

    public Transaction() {

    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
