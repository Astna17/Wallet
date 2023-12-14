package com.functionality.td_wallet;

import com.functionality.td_wallet.entity.Devise;
import com.functionality.td_wallet.entity.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class TdWalletApplication {

	public static void main(String[] args) {
			Devise euro = new Devise(1, "Euro", "EUR");
			Account account = new Account(1, "Current Account", 1000.0, LocalDateTime.now(), new ArrayList<>(), euro, "bank");
			System.out.println("Initial balance: " + account.getBalance());

			account.performTransaction("Purchase", 200.0, "debit");
			System.out.println("Balance after debit: " + account.getBalance());

			account.performTransaction("Salary", 500.0, "credit");
			System.out.println("Balance after credit: " + account.getBalance());
	}

}
