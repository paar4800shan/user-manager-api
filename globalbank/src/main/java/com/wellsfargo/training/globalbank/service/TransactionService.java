package com.wellsfargo.training.globalbank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.model.Transaction;
import com.wellsfargo.training.globalbank.repository.TransactionRepository;





@Service
@Transactional
public class TransactionService {
	@Autowired
	private TransactionRepository txrepo;
	
	
	public Transaction saveTransaction(Transaction trans) {
		return txrepo.save(trans);
	}

	public Optional<Transaction> findByID(long transid) {
			
			return txrepo.findById(transid);
		
	}

	

	public List<Transaction> transactionPeriod(Long userId, String transactionType, Timestamp tperiodto, Timestamp tperiodfrom) {
		// TODO Auto-generated method stub
		List<Transaction> txlist = txrepo.findBetweenDates(userId,transactionType);
		System.out.println(txlist.get(0).getTransid());
//		List<Transaction> results = new ArrayList<>();
//
//		for(Transaction transaction:txlist) {
//			if(transaction.getDate().after(tperiodfrom) && transaction.getDate().before(tperiodto))
//				results.add(transaction);
//		}

	    return	txlist;
	}

}


