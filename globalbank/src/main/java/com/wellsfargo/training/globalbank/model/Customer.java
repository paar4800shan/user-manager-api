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
    private String fname;

    @Column(name="last_name")
    private String lname;

    @Column(name="password")
    private String password;

     @Column(name="dob")
     @JsonFormat(pattern="yyyy-MM-dd")
     private Date dob;

    @Column(name="phone")
    private String phoneNo;
    
  

    @Column(name="city")
    private String city;

	public Customer() {
		super();
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city =city;
	}
    
}
