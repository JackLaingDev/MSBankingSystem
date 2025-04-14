package com.msbanking.services;

import com.msbanking.models.Customer;
import com.msbanking.repositories.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private Customer customer;
    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void closeCustomer() {
        customerRepo.delete(customer);
    }

    // ADD GET ACCOUNTS

    public int login(String username, String password) {
        Customer found = customerRepo.findByUsername(username);

        if (found == null) {
            return -1; // No account
        } else if (found.getIsClosed()) {
            return -2; // Closed
        } else if (!found.getPassword().equals(password)) {
            return -3; // Wrong password
        }

        this.customer = found;
        return 0; // Success
    }

    public int register(String username, String password, String firstName, String lastName) {
        Customer customer = new Customer(0, firstName, lastName, password, username);

        try {
            customerRepo.save(customer);
            return 0;
        } catch (DataIntegrityViolationException e) {
            return -1; // Username exists
        }
    }

    public int getCustomerID() {
        return this.customer.getCustomerID();
    }
}
