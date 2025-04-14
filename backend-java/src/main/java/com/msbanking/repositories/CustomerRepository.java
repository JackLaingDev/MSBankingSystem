package com.msbanking.repositories;

import com.msbanking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerID(int customerID);
}
