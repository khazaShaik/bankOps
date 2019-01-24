package com.symbo.dao;

import org.springframework.data.repository.CrudRepository;

import com.symbo.domain.SavingsAccount;


public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (int accountNumber);
}
