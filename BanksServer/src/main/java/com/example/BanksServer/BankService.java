package com.example.BanksServer;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BankService implements BankOperation {

	@Autowired
	BankRepository bankrepository;

	@Override
	public String transfer(RequestBodyClass req) {

		UserDetails userdetails1 = getAccount(req.getSenderAccount());
		UserDetails userdetails2 = getAccount(req.getReceiverAccount());

		if (userdetails1 == null && userdetails2 == null)
			return "There no such Account";
		long low = userdetails1.getBalance() - req.getAmount();

		if (userdetails1.getBalance() - req.getAmount() > 0 && low >= 1000) {

			userdetails1.setBalance(userdetails1.getBalance() - req.getAmount());

			userdetails1.addTransaction(UserDetails.getDateTime() + " " + req.getAmount() + "  tranfering to" + req.getReceiverAccount()
					+ " " + userdetails1.getBalance());

			bankrepository.save(userdetails1);
			userdetails2.setBalance(userdetails2.getBalance() + req.getAmount());

			userdetails2.addTransaction(UserDetails.getDateTime() + " " +  req.getAmount() + "  received from" + req.getSenderAccount()
					+ " " + userdetails2.getBalance());

			bankrepository.save(userdetails2);

			return "Amount : " +  req.getAmount()+ " after transfer and balance " + (userdetails1.getBalance() -  req.getAmount());
		}

		else if (userdetails1.getBalance() - req.getAmount() > 0 && low < 1000) {

			userdetails1.setBalance(userdetails1.getBalance() - req.getAmount());
			// bankrepository.save(userdetails1);
			userdetails2.setBalance(userdetails2.getBalance() + req.getAmount());
			// bankrepository.save(userdetails2);
			userdetails1.addTransaction(UserDetails.getDateTime() + " " + req.getAmount() + "  tranfering to"
					+ req.getReceiverAccount() + " " + userdetails1.getBalance());

			bankrepository.save(userdetails1);
			userdetails2.setBalance(userdetails2.getBalance() + req.getAmount());

			userdetails2.addTransaction(UserDetails.getDateTime() + " " + req.getAmount() + "  received from"
					+ req.getSenderAccount() + " " + userdetails2.getBalance());

			bankrepository.save(userdetails2);

			return "Amount : " + req.getAmount() + " has transfer and balance "
					+ (userdetails1.getBalance() - req.getAmount()) + "\n please maintain the minimum balance Rs1000";
		}

		else

			return "The  Account number " + userdetails1.getAccountNumber() + "'s is not having that much  Balance : "
					+ userdetails1.getBalance();

	}

	@Override
	public List<String> getTransactionHistory(RequestBodyClass req) {
		UserDetails userdetails = getAccount(req.getSenderAccount());

		return userdetails.getTransaction_History();
	}

	@Override
	public String deleteAccount(RequestBodyClass req) {

		UserDetails userdetails = getAccount(req.getSenderAccount());
		System.out.println(userdetails.getFirstName());
		if (userdetails.isStatus() == false)
			return "There no account with AccountNUmber Exist";
		else
			System.out.println("Delete ELse");
		userdetails.setStatus(false);
		bankrepository.save(userdetails);
		return "The " + " Account number " + userdetails.getAccountNumber() + "'s amount " + userdetails.getBalance()
				+ "is Widthdrawed and deleted successfully ";
	}

	@Override
	public String widthDraw(RequestBodyClass req) {

		UserDetails userdetails = getAccount(req.getSenderAccount());

		long low =userdetails.getBalance() - req.getAmount();

		if (userdetails == null)
			return "There no account with AccountNumber";
		else if (userdetails.getBalance() - req.getAmount() > 0 && low >= 1000) {

			userdetails.setBalance(userdetails.getBalance() - req.getAmount());
			userdetails.addTransaction(
					UserDetails.getDateTime() + " " +req.getAmount() + " widthrawal " + userdetails.getBalance());
			bankrepository.save(userdetails);

			return "Amount : " +req.getAmount()+ " has withdrawed and balance " + (userdetails.getBalance() - req.getAmount());
		} else if (userdetails.getBalance() -req.getAmount() > 0 && low < 1000) {

			userdetails.setBalance(userdetails.getBalance() - req.getAmount());
			userdetails.addTransaction(
					UserDetails.getDateTime() + " " +req.getAmount() + " widthrawal " + userdetails.getBalance());
			bankrepository.save(userdetails);

			return "Amount : " +req.getAmount() + " has withdrawed and balance " + (userdetails.getBalance() - req.getAmount())
					+ "/n please maintain the minimum balance Rs1000";
		} else
			return "The " + " Account number " + userdetails.getAccountNumber()
					+ "'s is not having that much amount Totaol Balance : " + userdetails.getBalance();

	}

	@Override
	public String viewBalance(RequestBodyClass req) {
		UserDetails userdetails;
		if ((userdetails = getAccount(req.getSenderAccount())) == null)

			return "There is no Account with ACcountnumber please check the number ";
		else

			return "Account Balance of Account " + userdetails.getFirstName() + " is :" + userdetails.getBalance();
	}

	@Override
	public String depositAmount(RequestBodyClass req) {
		long amount=req.getAmount();
		UserDetails userdetails = getAccount(req.getSenderAccount());
       System.out.println(userdetails.getBalance());
		if (userdetails == null)
			return "There no account with AccountNumber";

		else
           userdetails.setBalance(userdetails.getBalance() + amount);
		   userdetails.addTransaction(UserDetails.getDateTime() + " " + amount + " deposited " + userdetails.getBalance());
            bankrepository.save(userdetails);

		return "Account : " + userdetails.getAccountNumber() + " balance after deposit :" + userdetails.getBalance();
	}

	
	public List<UserDetails> getAllUser(BankList banklist, AccountType accounttype) {

		return (List<UserDetails>) bankrepository.findAll().stream().parallel().filter((UserDetails userdetails) -> {
			if (userdetails.isStatus() && userdetails.getBankName() == banklist
					&& userdetails.getType().equals(accounttype))
				return true;
			else
				return false;
		}).collect(Collectors.toList());

	}

	public String createAccount(UserDetails userdetailsparam) {

		userdetailsparam.setAccountNumber(generateAccountNumber());

		UserDetails userdetails;

		System.out.println(bankrepository.getObjectByPan(userdetailsparam.getPan_No()));

		if (!(bankrepository.getObjectByPan(userdetailsparam.getPan_No()) instanceof UserDetails)) {

			bankrepository.save(new UserDetails(userdetailsparam));

			return userdetailsparam.getAccountNumber();
		} else
			return "There is a Account already associated withh provide " + userdetailsparam.getPan_No()
					+ " pan number";
	}

	String getUser(RequestBodyClass req) {

		UserDetails userdetails = getAccount(req.getSenderAccount());

		if (userdetails == null)
			return "There is no such account Exists";

		else
			return userdetails.getFirstName() + " " + userdetails.getLastName() + " " + userdetails.getType() + " "
					+ " " + userdetails.getBalance();

	}

	String generateAccountNumber() {

		long accountNumber = new Random().nextLong();
		while (bankrepository.getObjectByAccountNumber(String.valueOf(accountNumber).substring(1, 12)) != null)
			accountNumber = new Random().nextLong();

		return String.valueOf(accountNumber).substring(1, 12);

	}

	UserDetails getAccount(String account) {
		
		Optional<UserDetails> optional = bankrepository.getObjectByAccountNumber(account);
		UserDetails userdetails = optional.get();
		System.out.println(userdetails.getFirstName());
		if (userdetails != null && userdetails.isStatus()) {

			return userdetails;
		} else
			return null;
	}

}
