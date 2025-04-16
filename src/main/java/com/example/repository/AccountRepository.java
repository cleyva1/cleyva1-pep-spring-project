package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByUsername(String username);

    Optional<Account> findAccountByUsernameAndPassword(String username, String password);

    Optional<Account> findAccountByAccountId(Integer accountId);
}
