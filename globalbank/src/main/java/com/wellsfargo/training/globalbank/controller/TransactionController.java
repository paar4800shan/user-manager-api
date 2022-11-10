package com.wellsfargo.training.globalbank.controller;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
	  public ResponseEntity transactionPost(@RequestBody Transaction transaction, @RequestHeader(value="Authorization") String token) {
		 Long userId = Long.parseLong(jwtUtil.extractUserId(token));
		 if (transaction.getTransactiontype().equalsIgnoreCase("Deposit")) {
	            Optional<Customer> customer = lservice.findById(userId);
	            Customer cust = customer.get();
	            cust.setAccountBalance(cust.getAccountBalance()+transaction.getAmount());

			 System.out.println("CUSTOMERRRR" + cust.getId());
	            

	            Date date = new Date();
				transaction.setDate(date);
				transaction.setCustomer(customer.get());

				txservice.saveTransaction(transaction);
	        }
			else if (transaction.getTransactiontype().equalsIgnoreCase("Withdraw")) {
				Optional<Customer> customer = lservice.findById(userId);
	            Customer cust = customer.get();

	            Long balance = cust.getAccountBalance()-(transaction.getAmount());

	            if(balance < 0) {
	            	 return new ResponseEntity<>("Insufficient Amount", HttpStatus.BAD_REQUEST);
	            }

				cust.setAccountBalance(balance);
	            Date date = new Date();
				transaction.setDate(date);
				transaction.setCustomer(cust);
	            txservice.saveTransaction(transaction);
			}
			
	       	 return new ResponseEntity<>("Transaction Success", HttpStatus.OK);
	       
	       
	    }
	 
//	 @RequestMapping(value = "/transactionPeriod", method = RequestMethod.GET)
//	    public String txperiod(Model model) {
//	        model.addAttribute("transactionType", "");
//	        model.addAttribute("tperiodfrom", "");
//	        model.addAttribute("tperiodto", "");
//	        return "deposit";
//	    }
//
		
		 @RequestMapping(value = "/transactionPeriod", method = RequestMethod.GET)
		  public ResponseEntity txperiodPost(@RequestParam("transactionType") String transactionType, @RequestParam("tperiodfrom") String tperiodfrom,@RequestParam("tperiodto") String tperiodto,@RequestHeader(value="Authorization") String token) throws ParseException {
			 System.out.println("FROM - " + tperiodfrom);
			 System.out.println("TO - " + tperiodto);
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			 Date formatted_tperiodfrom = format.parse(tperiodfrom);
			 Date formatted_tperiodto = format.parse(tperiodto);

			 if(formatted_tperiodfrom.after(formatted_tperiodto)) {
				return new ResponseEntity("Invalid period of time",HttpStatus.BAD_REQUEST);
			 }

			 Long userId = Long.parseLong(jwtUtil.extractUserId(token));
			 List<Transaction> transaction = txservice.transactionPeriod(userId,transactionType,formatted_tperiodfrom,formatted_tperiodto);
			 return new ResponseEntity<>(transaction, HttpStatus.OK);
		    }
			

}

// DB Format - 2022-11-09 23:03:48.819000
//2022-11-08 00:00:00.0



