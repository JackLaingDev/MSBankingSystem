package com.msbanking.controllers;

import com.msbanking.models.Account;
import com.msbanking.models.Customer;
import com.msbanking.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRESTController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        int status = customerService.register(
                customer.getUsername(), customer.getPassword(),
                customer.getFirstName(), customer.getLastName()
        );
        return status == 0 ? "Registration successful" : "Username already exists";
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        int status = customerService.login(customer.getUsername(), customer.getPassword());
        return switch (status) {
            case -1 -> "Account not found";
            case -2 -> "Account is closed";
            case -3 -> "Incorrect password";
            default -> "Login successful. Customer ID: " + status;
        };
    }

    @GetMapping("/{customerID}/accounts")
    public List<Account> getAccounts(@PathVariable int customerID) {
        return customerService.getAccounts(customerID);
    }

    @DeleteMapping("/{customerID}")
    public String closeCustomer(@PathVariable int customerID) {
        customerService.closeCustomer(customerID);
        return "Customer closed";
    }
}
