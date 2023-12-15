package com.functionality.td_wallet;

import com.functionality.td_wallet.entity.Account;
import com.functionality.td_wallet.entity.Devise;
import com.functionality.td_wallet.entity.Transaction;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TdWalletApplication {

	public static Account main(String[] args) {
		Account account = newAccount();

		LocalDateTime startDateTime = LocalDateTime.of(2023,12,01,12,30);
		LocalDateTime endDateTime = LocalDateTime.of(2023,12,06,16,00);

		List<Account.BalanceHistoryEntry> balanceHistoryEntries = account.getBalanceHistoryInInterval(startDateTime,endDateTime);

		for (Account.BalanceHistoryEntry entry : balanceHistoryEntries){
			System.out.println("date et heure: " + entry.getDateTime() + "solde: " + entry.getBalance());
		}
		return account;
	}

	private static Account newAccount(){
		Devise euro = new Devise(1,"euro","EUR");
		Account account = new Account(1,"compte courant",0,LocalDateTime.now(),new ArrayList<>(),euro,"banque");

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1,"Salaire",10000,LocalDateTime.of(2023,12,14,11,18),"credit"));

		account.setTransactions();

		return account;
	}

}
