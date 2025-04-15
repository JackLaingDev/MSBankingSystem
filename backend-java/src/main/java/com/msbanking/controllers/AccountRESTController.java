package com.msbanking.controllers;

import com.msbanking.models.Account;
import com.msbanking.models.Transaction;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRESTController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepo;

    @PostMapping("/deposit")
    public String deposit(@RequestParam int accountID, @RequestParam BigDecimal amount) {
        try {
            Account account = accountRepo.findById(accountID).orElse(null);
            if (account == null || account.getIsClosed()) return "Account not found or closed";

            accountService.deposit(account, amount);
            return "Deposit successful";
        } catch (Exception e) {
            return "Failed to deposit: " + e.getMessage();
        }
    }

    @PostMapping("/create")
    public String createAccount(@RequestBody Account account) {
        try {
            accountService.createAccount(account);
            return "Account created";
        } catch (Exception e) {
            return "Error creating account: " + e.getMessage();
        }
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam int accountID, @RequestParam BigDecimal amount) {
        try {
            Account account = accountRepo.findById(accountID).orElse(null);
            if (account == null || account.getIsClosed()) return "Account not found or closed";

            accountService.withdraw(account, amount);
            return "Withdrawal successful";
        } catch (Exception e) {
            return "Failed to withdraw: " + e.getMessage();
        }
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(@RequestParam int accountID) {
        Account account = accountRepo.findById(accountID).orElseThrow();
        return accountService.getTransactions(account);
    }

    @DeleteMapping("/{accountID}")
    public String closeAccount(@PathVariable int accountID){
        Account account = accountRepo.findById(accountID).orElseThrow();
        accountService.closeAccount(account);
        return "Account Closed";
    }
}

