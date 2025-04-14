package com.msbanking.services;

import java.math.BigDecimal;
import java.sql.*; // Import JDBC classes
import java.util.List;
import java.util.ArrayList;

import com.msbanking.models.Customer;
import com.msbanking.models.Transaction;
import com.msbanking.models.Account;

public class DatabaseService {

    // DB Settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bankingdb"; // Replace with your DB URL
    private static final String DB_USER = "root";     // Replace with your DB username
    private static final String DB_PASSWORD = "jacklaing"; // Replace with your DB password

    // Attributes
    private final Connection connection;
    private String sql;

    // Constructor
    public DatabaseService() {
        try {
            // Load the JDBC driver (not strictly needed in modern JDBC but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in a real application
            throw new RuntimeException("Error connecting to database", e); // Or rethrow as a custom exception
        }
    }

    // Methods
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void createCustomer(Customer customer) throws SQLException, SQLIntegrityConstraintViolationException{
        sql = "INSERT INTO customers (firstName, lastName, username, password) VALUES (?,?,?,?)";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getUsername());
            statement.setString(4, customer.getPassword());

            statement.executeUpdate();
        }
    }
    public void closeCustomer(Customer customer) throws SQLException{
        List<Account> custAccounts = getCustAccounts(customer); // needed to close all of customers accounts

        sql = "UPDATE customers SET isClosed = ? WHERE customerID = ?";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setBoolean(1, true);
            statement.setInt(2, customer.getCustomerID());

            statement.executeUpdate();
        }

        for(Account account : custAccounts){
            closeAccount(account);
        }
    }
    public Customer getCustomer(String userName) throws SQLException{
        sql = "SELECT * FROM customers WHERE username = ?";

        // Initialise customer
        Customer customer = new Customer(-1, "", "", "", "");

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setString(1, userName);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    // Only one result possible as CustomerID is a Unique column (Primary Key)
                    customer.setCustomerID(resultSet.getInt("customerID"));
                    customer.setFirstName(resultSet.getString("firstName"));
                    customer.setLastName(resultSet.getString("lastName"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setUsername(resultSet.getString("username"));
                    customer.setIsClosed(resultSet.getBoolean("isClosed"));
                }
                else{
                    return customer;
                }
            }
        }
        return customer;
    }
    public List<Account> getCustAccounts(Customer customer) throws SQLException{
        List<Account> accounts = new ArrayList<>();

        sql = "SELECT * FROM accounts WHERE customerID = ? && isClosed = 0";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setInt(1, customer.getCustomerID());

            // Get resultSet
            try(ResultSet resultSet = statement.executeQuery()){

                // Iterate through resultSet, adding each account to accounts
                while(resultSet.next()){

                    // Get results
                    int accountID = resultSet.getInt("accountID");
                    int customerID = resultSet.getInt("customerID");
                    int accountType = resultSet.getInt("accountType");
                    BigDecimal balance = resultSet.getBigDecimal("balance");

                    // Create account object with results
                    Account account = new Account(accountID,customerID, accountType, balance);

                    // Add account to accounts
                    accounts.add(account);
                }
            }
        }

        return accounts;
    }

    public void createAccount(Account account) throws SQLException{
        sql = "INSERT INTO accounts (customerID, accountID, accountType, balance) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setInt(1, account.getCustomerID());
            statement.setInt(2, account.getAccountID());
            statement.setInt(3, account.getAccountType());
            statement.setBigDecimal(4, account.getBalance());

            statement.executeUpdate();
        }
    }
    public void setAccountBalance(Account account, BigDecimal amount) throws SQLException{
        sql = "UPDATE accounts SET balance = ? WHERE accountID = ?";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setBigDecimal(1, amount);
            statement.setInt(2, account.getAccountID());

            statement.executeUpdate();
        }
    }
    public void closeAccount(Account account) throws SQLException{
        sql = "UPDATE accounts SET isClosed = ? WHERE accountID = ?";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setBoolean(1, true);
            statement.setInt(2, account.getAccountID());

            statement.executeUpdate();
        }
    }
    public Account getAccount(int accountID)throws SQLException{
        sql = "SELECT * FROM accounts WHERE accountID = ?";

        // Initialise account
        Account account = new Account(0, 0, 0, BigDecimal.ZERO);

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setInt(1, accountID);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    // Only one result possible as AccountID is a Unique column (Primary Key)
                    account.setAccountID(resultSet.getInt("accountID"));
                    account.setCustomerID(resultSet.getInt("customerID"));
                    account.setAccountType(resultSet.getInt("accountType"));
                    account.setBalance(resultSet.getBigDecimal("balance"));
                }
                else{
                    return null;
                }
            }
        }
        return account;
    }
    public List<Transaction> getAccTransactions(Account account) throws SQLException{
        List<Transaction> transactions = new ArrayList<>();

        sql = "SELECT * FROM transactions WHERE senderID = ? OR recipientID = ?";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setInt(1, account.getAccountID());
            statement.setInt(2, account.getAccountID());

            // Get resultSet
            try(ResultSet resultSet = statement.executeQuery()){

                // Iterate through resultSet, adding each account to accounts
                while(resultSet.next()){

                    // Get results
                    int transactionID = resultSet.getInt("transactionID");
                    int senderID = resultSet.getInt("senderID");
                    int recipientID = resultSet.getInt("recipientID");
                    BigDecimal amount = resultSet.getBigDecimal("amount");

                    // Create account object with results
                    Transaction transaction = new Transaction(transactionID,senderID, recipientID, amount);

                    // Add account to accounts
                    transactions.add(transaction);
                }
            }
        }

        return transactions;
    }

    public void createTransaction(Transaction transaction) throws SQLException{
        sql = "INSERT INTO transactions (senderID, recipientID, amount) VALUES (?,?,?)";

        try(PreparedStatement statement = prepareStatement(sql)){
            statement.setInt(1, transaction.getSenderID());
            statement.setInt(2, transaction.getRecipientID());
            statement.setBigDecimal(3, transaction.getAmount());

            statement.executeUpdate();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
