package com.symbo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.symbo.domain.PrimaryAccount;
import com.symbo.domain.PrimaryTransaction;
import com.symbo.domain.SavingsAccount;
import com.symbo.domain.SavingsTransaction;
import com.symbo.domain.User;
import com.symbo.message.response.ResponseMessage;
import com.symbo.service.AccountService;
import com.symbo.service.TransactionService;
import com.symbo.service.UserService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/primaryAccount")
	private ResponseEntity<PrimaryAccount> primaryAccount(Principal principal) {

		User user = userService.findByUsername(principal.getName()).get();
		PrimaryAccount primaryAccount = user.getPrimaryAccount();

		if (primaryAccount != null) {
			return new ResponseEntity<>(primaryAccount, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/primaryAccTransactions")
	private ResponseEntity<List<PrimaryTransaction>> primaryAccTransactions(Principal principal) {

		List<PrimaryTransaction> primaryTransactionList = transactionService
				.findPrimaryTransactionList(principal.getName());

		if (primaryTransactionList != null) {
			return new ResponseEntity<>(primaryTransactionList, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/savingsAccount")
	private ResponseEntity<SavingsAccount> savingsAccount(Principal principal) {

		User user = userService.findByUsername(principal.getName()).get();
		SavingsAccount savingsAccount = user.getSavingsAccount();

		if (savingsAccount != null) {
			return new ResponseEntity<>(savingsAccount, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/savingsAccTransactions")
	private ResponseEntity<List<SavingsTransaction>> savingAccTransactions(Principal principal) {

		List<SavingsTransaction> savingsTransactionList = transactionService
				.findSavingsTransactionList(principal.getName());

		if (savingsTransactionList != null) {
			return new ResponseEntity<>(savingsTransactionList, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestParam String accountType, @RequestParam String amount,
			Principal principal) {
		accountService.deposit(accountType, Double.parseDouble(amount), principal);

		return new ResponseEntity<>(new ResponseMessage(amount + " Deposited successfully!"), HttpStatus.OK);

	
	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@RequestParam String accountType, @RequestParam String amount,
			Principal principal) {

		if (accountService.checkSufficientBalance(accountType, principal, Double.parseDouble(amount))) {
			accountService.withdraw(accountType, Double.parseDouble(amount), principal);
			return new ResponseEntity<>(new ResponseMessage(amount + " Withdraw Successfull"), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Insuficient Balance!"), HttpStatus.NOT_FOUND);
		}

	}

}
