package com.example.BanksServer;

import org.springframework.data.mongodb.core.mapping.Document;


public enum BankList {

	SBI(1),
	IB(2),
	IOB(3),
	TMB(4);
	
	final int bankId;
   private BankList(int bankId){
	    this.bankId=bankId;
   }

}
