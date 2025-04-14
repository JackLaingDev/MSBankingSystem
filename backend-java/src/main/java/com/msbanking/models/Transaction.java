package com.msbanking.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @ManyToOne
    @JoinColumn(name = "senderID", nullable = false)
    private int senderID;

    @ManyToOne
    @JoinColumn(name = "recipientID", nullable = false)
    private int recipientID;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction() {};

    // Constructor
    public Transaction(int transactionID, int senderID, int recipientID, BigDecimal amount){
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.amount = amount;
    }

    // Getters and Setters
    public int getTransactionID(){return transactionID;}
    public void setTransactionID(int transactionID){this.transactionID = transactionID;}

    public int getSenderID(){return senderID;}
    public void setSenderID(int senderID){this.senderID = senderID;}

    public int getRecipientID(){return recipientID;}
    public void setRecipientID(int recipientID){this.recipientID = recipientID;}

    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal amount){this.amount = amount;}

}