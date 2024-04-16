package com.example.BanksServer;

import java.util.List;

public interface BankOperation {
 
  String createAccount(UserDetails userdetails);
  String depositAmount(RequestBodyClass req);//,long amount);	
  String widthDraw(RequestBodyClass req);
  String transfer(RequestBodyClass req);
  String viewBalance(RequestBodyClass req);
  List<String> getTransactionHistory(RequestBodyClass req);
  String deleteAccount(RequestBodyClass req);

  // String detailsChange();
}
