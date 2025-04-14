package com.msbanking.repositories;

import com.msbanking.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomer_CustomerID(int customerID);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.accountID = :accountID")
    void setAccountBalance(int accountID, BigDecimal balance);
}
