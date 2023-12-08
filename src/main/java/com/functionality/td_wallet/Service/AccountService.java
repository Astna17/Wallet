package com.functionality.td_wallet.Service;

import org.bibliotheque.Entity.Account;
import org.bibliotheque.Repository.AccountRepository;

import java.sql.SQLException;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account) {
        try {
            accountRepository.insert(account);
        } catch (SQLException e) {
            // Gérer l'exception
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            accountRepository.update(account);
        } catch (SQLException e) {
            // Gérer l'exception
            e.printStackTrace();
        }
    }

    public void deleteAccount(Account account) {
        try {
            accountRepository.delete(account);
        } catch (SQLException e) {
            // Gérer l'exception
            e.printStackTrace();
        }
    }
}
