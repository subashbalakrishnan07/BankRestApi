package com.example.BanksServer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BankRepository extends MongoRepository<UserDetails,String>{
  
  
 
	
   @Query("{accountNumber :?0}")
   Optional<UserDetails> getObjectByAccountNumber(String accountNumber);

   @Query("{BankId:? 0 }")
   UserDetails getObjectByBank(int id);
  
   @Query("{objectId:?0}")
   UserDetails getObjectObjectId(String str);

   @Query("{pan_No:?0}")
    UserDetails getObjectByPan(String string);
   
   
}
