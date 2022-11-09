package com.wellsfargo.training.globalbank.service;

//import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.globalbank.model.Loan;
import com.wellsfargo.training.globalbank.repository.LoanRepository;

import java.util.List;

@Service
@Transactional

public class LoanService {
	@Autowired
	private LoanRepository loanrepo;
	
	public Loan saveLoan(Loan loan) {
		return loanrepo.save(loan);
	}
	
	public List<Loan> getAllCustomers(){
		
		return loanrepo.findAll();
	}

}
