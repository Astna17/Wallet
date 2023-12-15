package com.functionality.td_wallet.Service;

import java.time.LocalDateTime;
import java.util.List;

public class AccountBalanceCalculator {
    public static class Transaction {
        private int id;
        private String label;
        private int amount;
        private String transactionType;
        private LocalDateTime dateTime;

        public Transaction(int id, String label, int amount, String transactionType, LocalDateTime dateTime) {
            this.id = id;
            this.label = label;
            this.amount = amount;
            this.transactionType = transactionType;
            this.dateTime = dateTime;
        }

        public int getAmount() {
            return amount;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }
    }

    public static int calculateBalance(List<Transaction> transactions, LocalDateTime dateTime) {
        int balance = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getDateTime().isAfter(dateTime)) {
                break;
            }

            if ("Credit".equals(transaction.getTransactionType())) {
                balance += transaction.getAmount();
            } else if ("Debit".equals(transaction.getTransactionType())) {
                balance -= transaction.getAmount();
            }
        }

        return balance;
    }
}
