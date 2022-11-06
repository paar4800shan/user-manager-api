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
    @Loan(name="id")
    private Long id;

   @Loan(name="Customer id")
    private String Customer id;

   @Loan(name="Branch")
    private String Branch;

    @Loan(name="Loan Amount")
    private String Loan Amount;
  
}
