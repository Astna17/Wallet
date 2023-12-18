package com.functionality.td_wallet;

import com.functionality.td_wallet.Service.AccountBalanceCalculator;
import com.functionality.td_wallet.Service.ExchangeRate;
import com.functionality.td_wallet.entity.Account;
import com.functionality.td_wallet.entity.Devise;
import com.functionality.td_wallet.entity.Transaction;
import com.functionality.td_wallet.entity.TransferHistory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TdWalletApplication {
	public static void main(String[] args) {
        // 1-
        Devise euro = new Devise(1, "Euro", "EUR");
        Devise ariary = new Devise(2, "Ariary", "MGA");
        Account account = new Account(1, "Current Account", 1000.0, LocalDateTime.now(), new ArrayList<>(), euro, "bank");
        System.out.println("Initial balance: " + account.getBalance());

        account.performTransaction("Purchase", 200.0, "debit");
        System.out.println("Balance after debit: " + account.getBalance());

        account.performTransaction("Salary", 500.0, "credit");
        System.out.println("Balance after credit: " + account.getBalance());

        // 4-
        Account account1 = new Account(1, "Account 1", 1000.0, euro, "bank");
        Account account2 = new Account(2, "Account 2", 500.0, euro, "bank");

        // Transfer 200 euros from Account 1 to Account 2
        LocalDateTime exchangeRateDate = null;
        account1.transferMoney(account2, 200.0, exchangeRateDate);

        //4-a
        // Obtenir l'historique des transferts entre une certaine plage de dates
        LocalDateTime startDate = LocalDateTime.parse("2023-12-01 00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-12-31 23:59");

        List<TransferHistory.TransferRecord> transfersInRange = TransferHistory.getTransfersInDateRange(startDate, endDate);

        for (TransferHistory.TransferRecord record : transfersInRange) {
            System.out.println("Source Account: " + record.sourceAccount.getName());
            System.out.println("Destination Account: " + record.destinationAccount.getName());
            System.out.println("Amount: " + record.amount);
            System.out.println("Transfer Date: " + record.transferDate);
            System.out.println("--------");
        }

        //4-b

        Account accountSource = new Account(1, "Account 1", 1000.0, euro, "bank");
        Account accountTarget = new Account(2, "Account 2", 500.0, ariary, "bank");

        // Set exchange rate for Euro to Ariary on a specific date
        exchangeRateDate = LocalDateTime.now();
        ExchangeRate.setExchangeRate("EUR", "MGA", 4600.0, exchangeRateDate);

        // Transfer 200 euros from Account 1 to Account 2 on the given date
        accountSource.transferMoney(accountTarget, 200.0, exchangeRateDate);

        //2- Exemple d'utilisation avec les données fournies
        List<AccountBalanceCalculator.Transaction> transactions = new ArrayList<>();
        transactions.add(new AccountBalanceCalculator.Transaction(1, "Salary", 100000, "credit", LocalDateTime.parse("2023-12-01 12:15 AM")));
        transactions.add(new AccountBalanceCalculator.Transaction(2, "Christmas gift", 50000, "debit", LocalDateTime.parse("2023-12-02 2:00 PM")));
        transactions.add(new AccountBalanceCalculator.Transaction(3, "New shoe", 20000, "debit", LocalDateTime.parse("2023-12-06 4:00 PM")));

        //3-
        LocalDateTime startTime = LocalDateTime.parse("2023-12-01T00:00");
        LocalDateTime endTime = LocalDateTime.parse("2023-12-06T23:59");

        TransferHistory transferHistory = getBalanceHistory(account, startTime, endTime);

        System.out.println("Balance history between " + startTime + " and " + endTime + ":");
        }

	public static TransferHistory getBalanceHistory(Account account, LocalDateTime startTime, LocalDateTime endTime) {
		TransferHistory transferHistory = new TransferHistory();
		double currentBalance = account.getBalance();

		for (Transaction transaction : account.getTransactions()) {
			LocalDateTime transactionDateTime = (LocalDateTime) transaction.getDateTime();

			if (transactionDateTime.isAfter(startTime) && transactionDateTime.isBefore(endTime)) {
				if ("credit".equals(transaction.getType())) {
					currentBalance += transaction.getAmount();
				} else if ("debit".equals(transaction.getType())) {
					currentBalance -= transaction.getAmount();
				}


                transferHistory.addTransfer(transaction.getAmount());
			}
		}

		return transferHistory;

	}
}