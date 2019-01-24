package com.symbo.message.request;

import javax.persistence.Column;

public class UserProfileForm {
		private Long userId;
	    private String username;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private boolean enabled=true;
	    private int p_accountNumber;
	    private int s_accountNumber;

	    

	public UserProfileForm() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getP_accountNumber() {
		return p_accountNumber;
	}

	public int getS_accountNumber() {
		return s_accountNumber;
	}

	public void setP_accountNumber(int p_accountNumber) {
		this.p_accountNumber = p_accountNumber;
	}

	public void setS_accountNumber(int s_accountNumber) {
		this.s_accountNumber = s_accountNumber;
	}

}
