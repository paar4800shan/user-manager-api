package com.wellsfargo.training.globalbank.controller;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.wellsfargo.training.globalbank.util.JwtUtil;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.wellsfargo.training.globalbank.model.Loan;
import com.wellsfargo.training.globalbank.model.Customer;
import com.wellsfargo.training.globalbank.model.Transaction;
import com.wellsfargo.training.globalbank.service.LoginService;
import com.wellsfargo.training.globalbank.service.TransactionService;



@RestController
@RequestMapping("/customer")
public class LoginController {
	
	@Autowired
	private LoginService lservice;
	
	
	
	//Request -> Front Controller -> Controller -> Response(Views-JSP).
	// Browse Home Page -> http://localhost:8085/ims/
	@RequestMapping("/")
	public String viewHomePage() {
		
		return "index";
	}
	
	
	@RequestMapping("/register")
	public String viewRegisterPage(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "register"; 
	}
	
	
	
	// PostRequest --> Controller ---> Service--> DealerRepository--> JPA Repository --> Database
    // DealerRepository--> Service ---> Controller ---> Response(views- jsp)
	@PostMapping("/saveCustomer")
	public Long saveCustomer(@RequestBody Customer customer) {
        lservice.saveCustomer(customer); 
        return customer.getId();
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/loginCustomer")
	public ResponseEntity loginCustomer(HttpServletRequest req,@ModelAttribute("customer") Customer customer) throws ParseException {

		JwtUtil jwtUtil = new JwtUtil();
		
		StringBuilder jb = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while((line=reader.readLine())!=null)
				jb.append(line);
			reader.close();
		}catch(Exception e) {System.out.println(e.getMessage());}
		JSONParser parser = new JSONParser();
	    JSONObject jsonObject = (JSONObject)parser.parse(jb.toString());
		
		Long customerid= (Long) jsonObject.get("userID");
        String pass=(String) jsonObject.get("password");
        System.out.println(customerid+pass);
        String pass2=encryptPass(pass);  //invokes encryptPass() method
      

         ModelAndView mav=null;
         Optional<Customer> d=lservice.findById(customerid); // Fetch record/object from Two Tables(Dealer & Address) based on emailId.
         
         if(d == null) {
		  return new ResponseEntity<>("Invalid Customer", HttpStatus.BAD_REQUEST);
        	// mav=new ModelAndView("login");
        	 //mav.addObject("error","Customer doesn't Exist");
         }
         else if(customerid==d.get().getId()&& pass2.equals(d.get().getPassword())){

			 final String jwt = jwtUtil.generateToken(customerid);
			 System.out.println(jwt);
			 return new ResponseEntity<>(String.format("%s;;;%s",customerid,jwt), HttpStatus.OK);
//        	 req.getSession().setAttribute("user",d.get().getFirstName());    // creating a session
//
//             mav = new ModelAndView("Operations");
//             mav.addObject(d);
         }
         else {
			 return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
        	 /*mav = new ModelAndView("login");
             mav.addObject("error", "Invalid Password");*/
         }
		//return new ResponseEntity<>("",HttpStatus.OK);
	}

	private String encryptPass(String pass) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = pass;
        String encodedString = encoder.encodeToString(
       normalString.getBytes(StandardCharsets.UTF_8) );
        return encodedString;
    }
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
        return  "index";
    }

}

