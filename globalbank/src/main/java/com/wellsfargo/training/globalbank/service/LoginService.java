package com.wellsfargo.training.globalbank.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.repository.CustomerRepository;

import java.util.List;

@Service
@Transactional

public class LoginService {
	@Autowired
	private CustomerRepository custrepo;
	
	public Customer saveCustomer(Customer customer) {
		return custrepo.save(customer);
	}
	public Optional<Customer> findById(Long customerid) {
		
		return custrepo.findById(customerid);
	}
	public Customer registerCustomer(Customer customer) {
		
		return custrepo.save(customer);
	}
	
	public List<Customer> getAllCustomers(){
		
		return custrepo.findAll();
	}



}
