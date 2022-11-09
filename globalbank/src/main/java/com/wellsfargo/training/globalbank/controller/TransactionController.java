package com.wellsfargo.training.globalbank.controller;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wellsfargo.training.globalbank.model.Branch;
import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.model.Transaction;
import com.wellsfargo.training.globalbank.service.LoginService;
import com.wellsfargo.training.globalbank.service.TransactionService;
import com.wellsfargo.training.globalbank.util.JwtUtil;







@RestController
public class TransactionController {
	@Autowired
	private TransactionService txservice;
	 @Autowired
	  private LoginService lservice;
	
	
//	public ModelAndView showEditProductPage(@RequestParam("id") int id) {
//        ModelAndView mav = new ModelAndView("edit_product");
//        Product product = pservice.get(id); //Fetch object based on a id.
//        mav.addObject("product", product);
//        return mav;
//    }	
	 @RequestMapping("/t")
		public String viewHomePage() {
			
			return "janu";
		}
	JwtUtil jwtUtil = new JwtUtil();
	@PostMapping("/tokenblah")
	public String tokenblah(@RequestBody String token) {

		return jwtUtil.extractUserId(token);

	}
	@RequestMapping(value = "/transactionProceed", method = RequestMethod.GET)
    public String transaction(Model model) {
        model.addAttribute("amount", "");
        model.addAttribute("transactionType", "");

        return "deposit";
    }
	
	
	 @RequestMapping(value = "/transactionProceed", method = RequestMethod.POST)
	  public ResponseEntity transactionPost(@ModelAttribute("amount") long amount, @ModelAttribute("transactionType") String transactionType,@RequestHeader(value="Authorization") String token) {
		 Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		 if (transactionType.equalsIgnoreCase("Deposit")) {
	            Optional<Customer> customer = lservice.findById(userId);
	            Customer cust = customer.get();
	            cust.setAccountBalance(cust.getAccountBalance()+amount);
	            

	            Date date = new Date();
	            Transaction trans = new Transaction(cust,Math.random(), transactionType, cust.getAccountBalance(),date);
	                txservice.saveTransaction(trans);
	        }
			else if (transactionType.equalsIgnoreCase("Withdraw")) {
				Optional<Customer> customer = lservice.findById(userId);
	            Customer cust = customer.get();
	            Long balance = cust.getAccountBalance()-(amount);
	            if(balance < 0) {
	            	 return new ResponseEntity<>("Insufficient Amount", HttpStatus.BAD_REQUEST);
	            }          
	           

	            Date date = new Date();
	            Transaction trans = new Transaction(cust,Math.random(), transactionType, cust.getAccountBalance(),date);
	            txservice.saveTransaction(trans);
			}
			
	       	 return new ResponseEntity<>("Transaction Success", HttpStatus.OK);
	       
	       
	    }
	 
	 @RequestMapping(value = "/transactionPeriod", method = RequestMethod.GET)
	    public String txperiod(Model model) {
	        model.addAttribute("transactionType", "");
	        model.addAttribute("tperiodfrom", "");
	        model.addAttribute("tperiodto", "");
	        return "deposit";
	    }
	 
		
		 @RequestMapping(value = "/transactionPeriod", method = RequestMethod.POST)
		  public ResponseEntity txperiodPost(@ModelAttribute("transactionType") String transactionType, @ModelAttribute("tperiodfrom") Date tperiodfrom,@ModelAttribute("tperiodto") Date tperiodto,@RequestHeader(value="Authorization") String token) {
			 Long userId = Long.parseLong(jwtUtil.extractUserId(token));
			 List<Transaction> transaction = txservice.transactionPeriod(userId,transactionType,tperiodfrom,tperiodto);
			 return new ResponseEntity<>(transaction, HttpStatus.OK);
		    }
			

}



