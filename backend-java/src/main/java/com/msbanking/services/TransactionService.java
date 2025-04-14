package com.msbanking.services;

import com.msbanking.models.Transaction;
import com.msbanking.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

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

}
