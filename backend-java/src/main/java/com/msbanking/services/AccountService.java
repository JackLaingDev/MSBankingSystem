package com.msbanking.services;

import com.msbanking.models.Transaction;

import com.msbanking.models.Account;
import com.msbanking.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final TransactionService transServ;

    public AccountService(AccountRepository accountRepo, TransactionService transServ) {
        this.accountRepo = accountRepo;
        this.transServ = transServ;
    }

    public void deposit(Account account, BigDecimal amount) throws SQLException {
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(account.getAccountID(), newBalance);
        }
    }

    public void withdraw(Account account, BigDecimal amount) throws SQLException {
        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(account.getAccountID(), newBalance);
        }
    }

    @Transactional
    public void makeTransaction(Transaction transaction) throws SQLException {
        Account sender = transaction.getSender();
        Account recipient = accountRepo.findById(transaction.getRecipient().getAccountID()).orElse(null);
        if (recipient == null) throw new SQLException("Recipient account not found");

        BigDecimal amount = transaction.getAmount();
        BigDecimal newSenderBalance = sender.getBalance().subtract(amount);
        BigDecimal newRecipientBalance = recipient.getBalance().add(amount);

        if (newSenderBalance.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(sender.getAccountID(), newSenderBalance);
            accountRepo.setAccountBalance(recipient.getAccountID(), newRecipientBalance);
            transServ.saveTransaction(transaction);
        } else {
            throw new SQLException("Insufficient funds or invalid amount");
        }
    }

    public void createAccount(Account account) {
        accountRepo.save(account);
    }

    public List<Transaction> getTransactions(Account account) {
        return transServ.getTransactionsForAccount(account);
    }
}
