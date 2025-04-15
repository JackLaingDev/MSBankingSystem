package com.msbanking.services;

import com.msbanking.models.Account;
import com.msbanking.models.Customer;
import com.msbanking.repositories.AccountRepository;
import com.msbanking.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;

    public CustomerService(CustomerRepository customerRepo, AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void closeCustomer(int customerID) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(() ->
                new IllegalArgumentException("Customer not found"));
        customerRepo.delete(customer);
    }

    public List<Account> getAccounts(int customerID) {
        return accountRepo.findByCustomer_CustomerID(customerID);
    }

    public int login(String username, String password) {
        Customer found = customerRepo.findByUsername(username);

        if (found == null) {
            return -1; // No account
        } else if (found.getIsClosed()) {
            return -2; // Closed
        } else if (!found.getPassword().equals(password)) {
            return -3; // Wrong password
        }

        return found.getCustomerID(); // Return ID on success
    }

    @Transactional
    public int register(String username, String password, String firstName, String lastName) {
        Customer customer = new Customer(0, firstName, lastName, password, username);

        try {
            customerRepo.save(customer);
            return 0;
        } catch (DataIntegrityViolationException e) {
            return -1; // Username exists
        }
    }
}
