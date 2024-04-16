package com.example.BanksServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankside")
public class BankController implements BankOperation {

	@Autowired
	BankService service;

	@GetMapping("/userdetail")
	String getDetails(@RequestBody RequestBodyClass req) {

		return service.getUser(req);
	}

	@Override
	@PostMapping("/newuser")
	public String createAccount(@RequestBody UserDetails userdetails) {

		return service.createAccount(userdetails);
	}

	@Override
	@PutMapping("/depositamount")
	public String depositAmount(@RequestBody RequestBodyClass req) {

		return service.depositAmount(req);
	}

	@Override
	@PutMapping("/widthdraw")
	public String widthDraw(@RequestBody RequestBodyClass req) {

		return service.widthDraw(req);
	}

	@Override
	@PostMapping("/transferto")
	public String transfer(RequestBodyClass req) {

		return service.transfer(req);
	}

	@Override
	@GetMapping("/viewbalance")
	public String viewBalance(@RequestBody RequestBodyClass req) {

		return service.viewBalance(req);
	}

	@Override
	@GetMapping("/gettransaction")
	public List<String> getTransactionHistory(@RequestBody RequestBodyClass req) {

		return service.getTransactionHistory(req);
	}

	@DeleteMapping("/deleteaccount")
	public String deleteAccount(@RequestBody RequestBodyClass req) {

		return service.deleteAccount(req);
	}

	@GetMapping("/getuserbytype")
	public List<UserDetails> getAllUser(@RequestBody BankList banklist, @RequestBody AccountType accounttype) {

		return service.getAllUser(banklist, accounttype);
	}
	
	
	
}
