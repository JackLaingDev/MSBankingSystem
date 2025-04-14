package com.msbanking.repositories;

import com.msbanking.models.Transaction;
import com.msbanking.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findBySenderOrRecipient(Account sender, Account recipient);
}
