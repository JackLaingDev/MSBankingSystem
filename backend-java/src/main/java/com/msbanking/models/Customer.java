package com.msbanking.models;

public class Customer {

    // Attributes
    private int customerID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isClosed;

    // Constructor
    public Customer(int customerID, String firstName, String lastName,String password, String username){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isClosed = false;
    }

    // Getters and Setters
    public int getCustomerID(){return customerID;}
    public void setCustomerID(int customerID){this.customerID = customerID;}

    public String getFirstName(){return firstName;}
    public void setFirstName(String customerID){this.firstName = firstName;}

    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean getIsClosed() {return isClosed;}
    public void setIsClosed(boolean closed) {isClosed = closed;}
}