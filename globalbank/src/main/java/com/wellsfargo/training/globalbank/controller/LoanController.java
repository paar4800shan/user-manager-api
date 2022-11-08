package com.wellsfargo.training.globalbank.controller;

import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.service.LoginService;
import com.wellsfargo.training.globalbank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wellsfargo.training.globalbank.model.Loan;
import com.wellsfargo.training.globalbank.service.LoanService;

import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class LoanController {
	
	@Autowired
	private LoanService loanservice;

	@Autowired
	private LoginService customerService;

	JwtUtil jwtUtil = new JwtUtil();

	@PostMapping("/saveLoan")
	public Loan saveLoan(@RequestBody Loan loan, @RequestHeader(value="Authorization") String token) {

		Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		Optional<Customer> customer = customerService.findById(userId);
		loan.setCustomer(customer.get());
        loanservice.saveLoan(loan);
        System.out.println("Successfully applied for loan "+loan.getLoanAmount()+" "+loan.getLoanId()+" "+loan.getCustomerID());
        return loan;
	}

	@PostMapping("/tokenblah")
	public String tokenblah(@RequestBody String token) {

		return jwtUtil.extractUserId(token);

	}
}