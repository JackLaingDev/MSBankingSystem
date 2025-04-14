package com.msbanking.services;

import com.msbanking.models.Account;
import com.msbanking.models.Transaction;
import com.msbanking.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    // Attributes
    private Transaction transaction;
    private final TransactionRepository transactionRepo;

    // Constructor
    public TransactionService(TransactionRepository transactionRepo){
        this.transactionRepo = transactionRepo;
    }

    // Getters and Setters
    public void setTransaction(Transaction transaction){this.transaction = transaction;}

    // Methods
    public void saveTransaction(){transactionRepo.save(transaction);}

    public List<Transaction> getTransactionsForAccount(Account account) {
        return transactionRepo.findBySenderOrRecipient(account, account);
    }


}
