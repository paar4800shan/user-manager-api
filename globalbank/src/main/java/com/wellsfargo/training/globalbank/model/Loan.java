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
@Table(name="Loan")

public class Loan {

  @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="Customerid")
    private long Customerid;

   @Column(name="Branch")
    private String Branch;

    @Column(name="LoanAmount")
    private long LoanAmount;

      public long getCustomerid() {
        return Customerid;
      }
      public void setCustomerid(long Customerid) {
        this.Customerid = Customerid;
      }
      public String getBranch() {
        return Branch;
      }
      public void setBranch(string Branch) {
        this.Branch = Branch;
      }
      public Long getLoanAmount() {
        return LoanAmount;
      }

      public void setLoanAmount(Long LoanAmount) {
        this.LoanAmount = LoanAmount;
      }
}
