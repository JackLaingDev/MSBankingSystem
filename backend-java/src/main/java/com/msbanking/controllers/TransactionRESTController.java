package com.msbanking.controllers;

import com.msbanking.models.Account;
import com.msbanking.models.Transaction;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.services.TransactionService;
import com.msbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRESTController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepo;

    @PostMapping("/send")
    public String sendMoney(@RequestBody Transaction transaction) {
        try {
            // Fetch sender from DB (to get the most up-to-date balance)
            Account sender = accountRepo.findById(transaction.getSender().getAccountID()).orElse(null);
            if (sender == null || sender.getIsClosed()) return "Sender account not found or closed";

            transaction.setSender(sender); // update transaction with fresh sender instance
            accountService.makeTransaction(transaction);
            return "Transaction successful";
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }
}

