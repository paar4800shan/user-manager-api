package com.wellsfargo.training.globalbank.model;



import java.sql.Date;

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
    private double transid;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="customerid", referencedColumnName="id")
	 private Customer customer;

    @Column(name="transactiontype")
    private String transactiontype;

    @Column(name="amount")
    private long amount;

    @Column(name="date")
    private Date date;
   

	public Transaction(Customer cust, double e, String transactiontype, long l,java.util.Date date2) {
		super();
		this.customer = cust;
		this.transid = e;
		this.transactiontype = transactiontype;
		this.amount = l;
		this.date = (Date) date2;
	}


	public double getTransid() {
		return transid;
	}


	public void setTransid(double transid) {
		this.transid = transid;
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
	
    
    

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	@Column(name="trans_type")
	private String trans_type;
	
	@Column(name="amount")
	private String amount;
	
	

}
