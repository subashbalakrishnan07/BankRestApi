package com.example.BanksServer;

public class RequestBodyClass {

	String senderAccount;
	String receiverAccount;
	long amount;
	public RequestBodyClass() {
	}
	public RequestBodyClass(String senderAccount, long amount) {
		
		this.senderAccount = senderAccount;
	
		this.amount = amount;
	}
	
	
	public RequestBodyClass(String senderAccount, String receiverAccount, long amount) {
	
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.amount = amount;
	}
	
	public String getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
}
