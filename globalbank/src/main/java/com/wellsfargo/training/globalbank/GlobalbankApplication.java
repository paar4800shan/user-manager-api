package com.wellsfargo.training.globalbank;

import com.wellsfargo.training.globalbank.model.Branch;
import com.wellsfargo.training.globalbank.repository.BranchRepository;
import com.wellsfargo.training.globalbank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class GlobalbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalbankApplication.class, args);
	}

	@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		private BranchRepository branchRepository;

		@Autowired
		private BranchService branchService;

		@Override
		public void run(String... args) throws Exception {

			if (branchService.getAllBranches().size() > 0) {
				return;
			}

			branchRepository.save(new Branch("BranchA"));
			branchRepository.save(new Branch("BranchB"));
			branchRepository.save(new Branch("BranchC"));
			branchRepository.save(new Branch("BranchD"));
			branchRepository.save(new Branch("BranchE"));
			branchRepository.save(new Branch("BranchF"));
		}
	}

}
