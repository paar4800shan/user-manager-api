package com.wellsfargo.training.globalbank.service;

import com.wellsfargo.training.globalbank.model.Branch;
import com.wellsfargo.training.globalbank.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
