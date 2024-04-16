package com.example.BanksServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;

@Document(collection = "BankUser")
public class UserDetails {
	
	
	@Id
    private String objectId;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String pan_No;
	private String phone_Number;
	private String email_Id;
	private String address;
	private AccountType type;
	private boolean status=true;
	private List<String> transaction_History=new ArrayList<>();
	private BankList bankId;
	private int branchId;
	private long balance=000;

	public UserDetails() {
		
	}
	
	public UserDetails( String firstName, String lastName, String pan_No, String phone_Number,
			String email_Id, String address, AccountType type, BankList bankId, int branchId) {
		
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.pan_No = pan_No;
		this.phone_Number = phone_Number;
		this.email_Id = email_Id;
		this.address = address;
		this.type = type;
		this.bankId = bankId;
		this.branchId = branchId;
		
		
	}





	public UserDetails(UserDetails userdetails) {
 
		this.firstName = userdetails.getFirstName();
		this.lastName = userdetails.getLastName();
		this.pan_No = userdetails.getPan_No();
		this.phone_Number = userdetails.getPhone_Number();
		this.email_Id = userdetails.getEmail_Id();
		this.address = userdetails.getAddress();
		this.status = true;
		this.bankId = userdetails.getBankName();
		this.branchId = userdetails.getBranchId();
		accountNumber = userdetails.getAccountNumber();
		transaction_History.add("Account Created Successfully " + accountNumber + " At " + getDateTime());

	}

	
	  
	  
	 
	static String getDateTime() {
       
		Date date=new Date();
       
		return date.toGMTString();
	}
	
	public String getObjectID() {
		return objectId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountnumber) {
		this.accountNumber = accountnumber;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public BankList getBankName() {
		return bankId;
	}

	public void setBankName(BankList bankId) {
		this.bankId = bankId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchName(int branchName) {
		this.branchId = branchName;
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

	public String getPan_No() {
		return pan_No;
	}

	public void setPan_No(String pan_No) {
		this.pan_No = pan_No;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getTransaction_History() {
		return transaction_History;
	}

	public void addTransaction(String transactionLog) {
		this.transaction_History.add(transactionLog);
	}

    

 }




 