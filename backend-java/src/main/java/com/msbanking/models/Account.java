package com.msbanking.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @ManyToOne
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> receivedTransactions;

    @Column(name = "accountType")
    private int accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "isClosed")
    private boolean isClosed;

    public Account() {};

    // Constructor
    public Account(int accountID, Customer customer, int accountType, BigDecimal balance) {
        this.accountID = accountID;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = balance;
        this.isClosed = false;
    }

    // Getters and Setters
    public int getAccountID() {return accountID;}
    public void setAccountID(int accountID) {this.accountID = accountID;}

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    public int getAccountType(){return accountType;}
    public void setAccountType(int accountType){this.accountType = accountType;}

    public BigDecimal getBalance(){return balance;}
    public void setBalance(BigDecimal balance){this.balance = balance;}

    public boolean getIsClosed() {return isClosed;}
    public void setClosed(boolean closed) {isClosed = closed;}

}