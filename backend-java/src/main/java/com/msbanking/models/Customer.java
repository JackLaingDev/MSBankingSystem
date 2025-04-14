package com.msbanking.models;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isClosed")
    private boolean isClosed;

    public Customer() {} // required no arg constructor for JPA

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
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean getIsClosed() {return isClosed;}
    public void setIsClosed(boolean closed) {isClosed = closed;}
}