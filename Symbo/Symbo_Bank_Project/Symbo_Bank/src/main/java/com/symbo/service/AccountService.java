package com.symbo.service;

import java.math.BigDecimal;
import java.security.Principal;

import com.symbo.domain.PrimaryAccount;
import com.symbo.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	
	void deposit(String accountType, double amount, Principal principal);
	void withdraw(String accountType, double amount, Principal principal);
	boolean checkSufficientBalance(String accountType,Principal principal,double amount);


}
