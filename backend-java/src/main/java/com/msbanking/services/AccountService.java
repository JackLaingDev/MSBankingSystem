package com.msbanking.services;

import com.msbanking.models.Transaction;

import com.msbanking.models.Account;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService{

    // Attributes
    private Account account;
    private final AccountRepository accountRepo;
    private final TransactionService transServ;

    // Constructor
    public AccountService(AccountRepository accountRepo,TransactionService transServ) {
        this.accountRepo = accountRepo;
        this.transServ = transServ;
    }

    // Getters and Setters
    public Account getAccount(){return account;}
    // ADD GET TRANSACTIONS
    public int getAccountID(){return account.getAccountID();}
    public BigDecimal getAccBalance(){return account.getBalance();}
    public void setAccount(Account account){this.account = account;}

    // Methods
    public void createAccount() {
        accountRepo.save(account);
    }
    public void closeAccount(){
        accountRepo.delete(account);
    }
    public void makeTransaction(Transaction transaction){

        transServ.setTransaction(transaction);

        Account recipientAccount = accountRepo.findById(transaction.getRecipientID()).orElse(null);;

        BigDecimal amount = transaction.getAmount();

        BigDecimal newAmountSender = this.account.getBalance().subtract(amount);
        BigDecimal newAmountRecipient = recipientAccount.getBalance().add(amount);

        // Check if sender account has enough money, and that the amount sent is valid
        if(newAmountSender.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(account.getAccountID(), newAmountSender);
            accountRepo.setAccountBalance(recipientAccount.getAccountID(), newAmountRecipient);
            account.setBalance(newAmountSender);
            transServ.saveTransaction();
        }
    }
    public void deposit(BigDecimal amount) throws SQLException{
        BigDecimal newBalance = account.getBalance().add(amount);
        if(newBalance.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(account.getAccountID(), newBalance);
            account.setBalance(newBalance);
        }
    }
    public void withdraw(BigDecimal amount) throws SQLException{
        BigDecimal newBalance = account.getBalance().subtract(amount);
        if(newBalance.compareTo(BigDecimal.ZERO) >= 0 && amount.compareTo(BigDecimal.ZERO) >= 0) {
            accountRepo.setAccountBalance(account.getAccountID(), newBalance);
            account.setBalance(newBalance);
        }
    }

}