package com.wellsfargo.training.globalbank.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.training.globalbank.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	public Optional<Loan> findByLoanId(Long loanId);
	
	/*Custom Query Using JPQL
	//@Query("SELECT new com.coforge.training.ims.model.DealerAddress(d.id,d.email,d.fname,d.lname,"
		+ "d.password,d.dob,d.phoneNo,a.street,a.city,a.pincode) "
			+ "FROM Dealer d INNER JOIN d.address a");
	//public List<DealerAddress>  fetchDealerInnerJoin();*/
	
}
