package com.wellsfargo.training.globalbank.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;



/*
The @Entity annotation specifies that the class is an entity and is mapped to a database table. 
The @Table annotation specifies the name of the database table to be used for mapping. 
*/
@Entity
@Table(name="Loan")

public class Loan {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="Loanid")
  private Long loanId;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="customerid", referencedColumnName="id")
  private Customer customer;
  
   @Column(name="Branch")
    private String branch;

    @Column(name="LoanAmount")
    private Long loanAmount;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Long getCustomerID() {
		return customer.getId();
	}
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}
}