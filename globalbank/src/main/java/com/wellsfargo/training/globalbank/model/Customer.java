package com.wellsfargo.training.globalbank.model;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Base64;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
The @Entity annotation specifies that the class is an entity and is mapped to a database table. 
The @Table annotation specifies the name of the database table to be used for mapping. 
*/
@Entity
@Table(name="customer")
public class Customer {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="occupation")
    private String occupation;

    @Column(name="first_name")
    private String FirstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

     @Column(name="dob")
     @JsonFormat(pattern="yyyy-MM-dd")
     private Date dob;

    @Column(name="phoneNo")
    private String contact;

    @Column(name="city")
    private String city;
    
    @Column(name="middle_name")
    private String middleName;
    
    @Column(name="AccountBalance")
    private long accountBalance;
    
    
    

	public Customer() {
		super();
		accountBalance = 0;
	}
	

	public long getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(long l) {
		this.accountBalance = l;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		System.out.println("Occupation:"+ occupation);
		this.occupation = occupation;
	}


	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("First :"+password);
		Base64.Encoder encoder = Base64.getEncoder();  // encrypt password in database field
        String normalString = password;
        System.out.println(password +" "+"normal :"+normalString);
        String encodedString = encoder.encodeToString(
        normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city =city;
	}
    
}
