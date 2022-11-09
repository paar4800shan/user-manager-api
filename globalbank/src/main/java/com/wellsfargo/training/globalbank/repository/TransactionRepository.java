package com.wellsfargo.training.globalbank.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.training.globalbank.model.Loan;
import com.wellsfargo.training.globalbank.model.Transaction;





@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
		
	@Query(value = "SELECT t.transactiontype t.amount FROM  transaction t where t.customerid  = ?1 and t.date between ?2 and ?3", nativeQuery = true)
	List<Transaction> findBetweenDates(long userid,Date tperiodfrom, Date tperiodto);

}
