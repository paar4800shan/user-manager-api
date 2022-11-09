package com.wellsfargo.training.globalbank.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")

public class Transaction {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="transid")
    private Long transid;
	
	 @ManyToOne(cascade = CascadeType.MERGE)
	 @JoinColumn(name="customerid", referencedColumnName="id")
	 private Customer customer;

    @Column(name="transactiontype")
    private String transactiontype;

    @Column(name="amount")
    private Long amount;

    @Column(name="date")
    private Date date;
   
	public Transaction() {

	}
	public Transaction(String transactiontype, Long l,java.util.Date date2) {
		super();
//		this.customer = cust;
		this.transactiontype = transactiontype;
		this.amount = l;
		this.date = date2;
	}


	public String getTransactiontype() {
		return transactiontype;
	}


	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}


	public long getAmount() {
		return amount;
	}


	public void setAmount(long amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
    
    

	
	

}
