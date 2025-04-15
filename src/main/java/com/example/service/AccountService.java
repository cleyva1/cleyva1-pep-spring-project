package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.repository.AccountRepository;

import java.util.Optional;

@Transactional
@Service
public class AccountService {
    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) throws DuplicateUsernameException {
        if (accountRepository.findAccountByUsername(account.getUsername()).isPresent()) {
            throw new DuplicateUsernameException();
        }
        else if (account.getPassword().length() < 4 || account.getUsername() == "") {
            throw new IllegalArgumentException();
        }
        else {
            return accountRepository.save(account);
        }
    }

    public Account login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsernameAndPassword(username, password);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        else {
            return null;
        }
    }
}
