package com.wellsfargo.training.globalbank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	

	public List<Transaction> transactionPeriod(Long userId, String transactionType, Date tperiodfrom, Date tperiodto) throws ParseException {
		// TODO Auto-generated method stub

		List<Transaction> txlist = txrepo.findBetweenDates(userId,transactionType);
		List<Transaction> results = new ArrayList<>();

		for(Transaction transaction:txlist) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(strDate.format(transaction.getDate()));
			String formattedTime = output.format(d);
			Date finalDate = output.parse(formattedTime);

			if(!tperiodfrom.after(finalDate) && !tperiodto.before(finalDate))
				results.add(transaction);
		}

		System.out.println(results);

	    return results;
	}

}




