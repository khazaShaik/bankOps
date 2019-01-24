package com.symbo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.symbo.domain.PrimaryAccount;
import com.symbo.domain.Recipient;
import com.symbo.domain.SavingsAccount;
import com.symbo.domain.User;
import com.symbo.message.response.ResponseMessage;
import com.symbo.service.AccountService;
import com.symbo.service.TransactionService;
import com.symbo.service.UserService;


@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;

	@PostMapping("/betweenAccounts")
	public ResponseEntity<?> withdraw(@RequestParam String transferFrom, @RequestParam String amount, @RequestParam String transferTo,
			Principal principal) {
		User user = userService.findByUsername(principal.getName()).get();
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
			
		if (accountService.checkSufficientBalance(transferFrom, principal, Double.parseDouble(amount))) {
			try {
				transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount, primaryAccount, savingsAccount);
			} catch (Exception e) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Trnsfer Failed!"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(new ResponseMessage(amount + " Trnsfer Successfull"), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Insuficient Balance!"), HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/recipient")
	private ResponseEntity<List<Recipient>> recipientGet(Principal principal) {
		List<Recipient> recipientList = transactionService.findRecipientList(principal);

	
		if (recipientList != null) {
			return new ResponseEntity<>(recipientList, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/recipient/save")
	public ResponseEntity<?> recipientPost(@RequestBody Recipient recipient, Principal principal) {
		
		User user = userService.findByUsername(principal.getName()).get();
		recipient.setUser(user);
		Recipient r = transactionService.saveRecipient(recipient);
		if(r!=null) {
			return new ResponseEntity<>(new ResponseMessage(" Benfi Successfull"), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Insuficient Balance!"), HttpStatus.NOT_FOUND);
		}

	}
	
	
	@DeleteMapping(value = "/recipient/delete")
	@Transactional
	public  ResponseEntity<List<Recipient>> recipientDelete(@RequestParam(value = "recipientName") String recipientName, Principal principal) {

		transactionService.deleteRecipientByName(recipientName);

		List<Recipient> recipientList = transactionService.findRecipientList(principal);

		return new ResponseEntity<>(recipientList, HttpStatus.OK);
	}
	
	@PostMapping("/toSomeoneElse")
	public ResponseEntity<?> toSomeoneElsePost(@RequestParam("recipientName") String recipientName, @RequestParam("accountType") String accountType, @RequestParam("amount") String amount,Principal principal) {
		
		User user = userService.findByUsername(principal.getName()).get();
		Recipient recipient = transactionService.findRecipientByName(recipientName);
		
		if (!accountService.checkSufficientBalance(accountType, principal, Double.parseDouble(amount))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Insuficient Balance!"), HttpStatus.NOT_FOUND);
		}
		else if(user.getRecipientList().contains(recipient)) {
		transactionService.toSomeoneElseTransfer(recipient, accountType, amount, user.getPrimaryAccount(),
				user.getSavingsAccount());
		return new ResponseEntity<>(new ResponseMessage(amount + " Trnsfer To "+recipient.getName()+" Successfull"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseMessage("Fail -> "+recipient.getName()+" Beneficiary Not register!"), HttpStatus.NOT_FOUND);

		}
		
	}
	
}
