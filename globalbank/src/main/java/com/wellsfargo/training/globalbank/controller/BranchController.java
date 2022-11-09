package com.wellsfargo.training.globalbank.controller;

import com.wellsfargo.training.globalbank.model.Branch;
import com.wellsfargo.training.globalbank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/all")
    public ResponseEntity getAllBranches() {

        List<Branch> branches = branchService.getAllBranches();

        return new ResponseEntity(branches, HttpStatus.OK);
    }


}
