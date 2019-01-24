package com.symbo.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SavingsTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long st_id;
	private Date st_date;
	private String st_description;
	private String st_type;
	private String st_status;
	private double st_amount;
	private BigDecimal st_availableBalance;
	
	@ManyToOne
	@JoinColumn(name = "savings_account_id")
	private SavingsAccount savingsAccount;

	public SavingsTransaction() {

	}

	public SavingsTransaction(Date date, String description, String type, String status, double amount,
			BigDecimal availableBalance, SavingsAccount savingsAccount) {
		super();
		this.st_date = date;
		this.st_description = description;
		this.st_type = type;
		this.st_status = status;
		this.st_amount = amount;
		this.st_availableBalance = availableBalance;
		this.savingsAccount = savingsAccount;
	}

	
	public Long getSt_id() {
		return st_id;
	}

	public void setSt_id(Long st_id) {
		this.st_id = st_id;
	}

	public Date getSt_date() {
		return st_date;
	}

	public void setSt_date(Date st_date) {
		this.st_date = st_date;
	}

	public String getSt_description() {
		return st_description;
	}

	public void setSt_description(String st_description) {
		this.st_description = st_description;
	}

	public String getSt_type() {
		return st_type;
	}

	public void setSt_type(String st_type) {
		this.st_type = st_type;
	}

	public String getSt_status() {
		return st_status;
	}

	public void setSt_status(String st_status) {
		this.st_status = st_status;
	}

	public double getSt_amount() {
		return st_amount;
	}

	public void setSt_amount(double st_amount) {
		this.st_amount = st_amount;
	}

	public BigDecimal getSt_availableBalance() {
		return st_availableBalance;
	}

	public void setSt_availableBalance(BigDecimal st_availableBalance) {
		this.st_availableBalance = st_availableBalance;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

}
