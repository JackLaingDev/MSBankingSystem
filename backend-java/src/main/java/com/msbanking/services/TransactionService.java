package com.msbanking.services;

import com.msbanking.models.Account;
import com.msbanking.models.Transaction;
import com.msbanking.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo){
        this.transactionRepo = transactionRepo;
    }

    public void saveTransaction(Transaction transaction){
        transactionRepo.save(transaction);
    }

    public List<Transaction> getTransactionsForAccount(Account account) {
        return transactionRepo.findBySenderOrRecipient(account, account);
    }
}
