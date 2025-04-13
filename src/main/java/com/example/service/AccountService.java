package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.repository.AccountRepository;

@Transactional
@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) throws DuplicateUsernameException {
        if (accountRepository.findAccountByUsername(account.getUsername()) != null) {
            throw new DuplicateUsernameException();
        }
        else {
            return accountRepository.save(account);
        }
    }

    public Boolean login(String username, String password) {
        if (accountRepository.findAccountByUsernameAndPassword(username, password) != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
