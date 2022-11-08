package com.wellsfargo.training.globalbank.controller;

import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.service.LoginService;
import com.wellsfargo.training.globalbank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity saveLoan(@RequestBody Loan loan, @RequestHeader(value="Authorization") String token) {
		System.out.println("loan - " + token);
		System.out.println(loan.getBranch().getId());
		System.out.println(loan.getBranch().getName());
		Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		Optional<Customer> customer = customerService.findById(userId);
		loan.setCustomer(customer.get());
        loanservice.saveLoan(loan);
        System.out.println("Successfully applied for loan "+loan.getLoanAmount()+" "+loan.getLoanId()+" "+loan.getCustomer().getId());
        return new ResponseEntity(String.format("Loan ID - %d of amount %d is applied at branch %s",loan.getLoanId(),loan.getLoanAmount(),loan.getBranch().getName()), HttpStatus.OK);
	}

	@PostMapping("/tokenblah")
	public String tokenblah(@RequestBody String token) {

		return jwtUtil.extractUserId(token);

	}
}