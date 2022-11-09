package com.wellsfargo.training.globalbank.repository;

import com.wellsfargo.training.globalbank.model.Branch;
import com.wellsfargo.training.globalbank.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    public Optional<Branch> findById(Long id);
}
