package com.msbanking.repositories;

import com.msbanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomer_CustomerID(int customerID);
}
