package models;

public class Account {
    private int accountID;
    private int customerID;
    private int accountType;
    private int balance;
    private boolean isClosed;

    public Account(int accountID, int customerID, int accountType, int balance) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.accountType = accountType;
        this.balance = balance;
        this.isClosed = false;
    }

    // Getters and Setters
    public int getAccountID() {return accountID;}
    public void setAccountID(int accountID) {this.accountID = accountID;}

    public int getCustomerID(){return customerID;}
    public void setCustomerID(int customerID){this.customerID = customerID;}

    public int getAccountType(){return accountType;}
    public void setAccountType(int accountType){this.accountType = accountType;}

    public int getBalance(){return balance;}
    public void setBalance(int balance){this.balance = balance;}

    public boolean isClosed(){return isClosed;}
    public void setIsClosed(boolean isClosed){this.isClosed = isClosed;}

}