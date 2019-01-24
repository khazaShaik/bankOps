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
public class PrimaryTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pt_id;
	private Date pt_date;
	private String pt_description;
	private String pt_type;
	private String pt_status;
	private double pt_amount;
	private BigDecimal pt_availableBalance;
	
	@ManyToOne
	@JoinColumn(name = "primary_account_id")
	private PrimaryAccount primaryAccount;

	public PrimaryTransaction() {

	}

	public PrimaryTransaction(Date date, String description, String type, String status, double amount,
			BigDecimal availableBalance, PrimaryAccount primaryAccount) {
		this.pt_date = date;
		this.pt_description = description;
		this.pt_type = type;
		this.pt_status = status;
		this.pt_amount = amount;
		this.pt_availableBalance = availableBalance;
		this.primaryAccount = primaryAccount;
	}

	

	public Long getPt_id() {
		return pt_id;
	}

	public void setPt_id(Long pt_id) {
		this.pt_id = pt_id;
	}

	public Date getPt_date() {
		return pt_date;
	}

	public void setPt_date(Date pt_date) {
		this.pt_date = pt_date;
	}

	public String getPt_description() {
		return pt_description;
	}

	public void setPt_description(String pt_description) {
		this.pt_description = pt_description;
	}

	public String getPt_type() {
		return pt_type;
	}

	public void setPt_type(String pt_type) {
		this.pt_type = pt_type;
	}

	public String getPt_status() {
		return pt_status;
	}

	public void setPt_status(String pt_status) {
		this.pt_status = pt_status;
	}

	public double getPt_amount() {
		return pt_amount;
	}

	public void setPt_amount(double pt_amount) {
		this.pt_amount = pt_amount;
	}

	public BigDecimal getPt_availableBalance() {
		return pt_availableBalance;
	}

	public void setPt_availableBalance(BigDecimal pt_availableBalance) {
		this.pt_availableBalance = pt_availableBalance;
	}

	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

}
