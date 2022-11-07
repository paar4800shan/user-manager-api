package com.wellsfargo.training.globalbank.model;

mport java.nio.charset.StandardCharsets;
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
   @Loan(name="Customerid")
    private String Customerid;

   @Loan(name="Branch")
    private String Branch;

    @Loan(name="LoanAmount")
    private String LoanAmount;
  
       public Customer id() {
        super();
      }

      public String getCustomerid() {
        return Customerid;
      }

      public void setCustomerid(Long id) {
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
