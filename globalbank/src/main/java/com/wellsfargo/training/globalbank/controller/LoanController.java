package com.wellsfargo.training.globalbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.globalbank.model.Loan;
import com.wellsfargo.training.globalbank.service.LoanService;


@RestController
@RequestMapping("/customer")
public class LoanController {
	
	@Autowired
	private LoanService loanservice;
	

	@PostMapping("/saveLoan")
	public Loan saveLoan(@RequestBody Loan loan) { 
        loanservice.saveLoan(loan);
        System.out.println("Successfully applied for loan "+loan.getLoanAmount()+" "+loan.getLoanId()+" "+loan.getCustomerID());
        return loan;
	}
}