package com.msbanking.controllers;

import com.msbanking.models.Account;
import com.msbanking.models.Transaction;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.services.TransactionService;
import com.msbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            Account sender = accountRepo.findById(transaction.getSender().getAccountID()).orElse(null);
            if (sender == null || sender.getIsClosed()) return "Sender account not found or closed";

            transaction.setSender(sender); // ensure latest sender balance
            accountService.makeTransaction(transaction);
            return "Transaction successful";
        } catch (Exception e) {
            return "Transaction failed: " + e.getMessage();
        }
    }

    @GetMapping("/history/{accountID}")
    public List<Transaction> getTransactionHistory(@PathVariable int accountID) {
        Account account = accountRepo.findById(accountID).orElse(null);
        if (account == null) return List.of(); // return empty list if not found
        return transactionService.getTransactionsForAccount(account);
    }
}
