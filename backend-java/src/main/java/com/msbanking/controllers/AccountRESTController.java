package com.msbanking.controllers;

import com.msbanking.models.Account;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
}

