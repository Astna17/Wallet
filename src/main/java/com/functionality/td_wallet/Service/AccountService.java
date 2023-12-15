package com.functionality.td_wallet.Service;

<<<<<<< HEAD
import com.functionality.td_wallet.Repository.AccountRepository;
import com.functionality.td_wallet.entity.Account;


=======
>>>>>>> b30c83e0e528c45518e255fb42aad9dd18253cab
import java.sql.SQLException;

import com.functionality.td_wallet.Repository.AccountRepository;
import com.functionality.td_wallet.entity.Account;
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
