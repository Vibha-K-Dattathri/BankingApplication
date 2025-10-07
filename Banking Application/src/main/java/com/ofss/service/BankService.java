package com.ofss.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ofss.model.Bank;
import com.ofss.model.BankRepository;
@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	
	public ResponseEntity<Object> addABank(Bank bank){
		// Saves a given entity. Use the returned instance for further
		// operations as the save operation might have changed theentity instance completely.
		// save() method will generate required INSERT query
		bankRepository.save(bank);
		return ResponseEntity.ok("Bank added successfully!");
	}
	
	public List<Bank> listBanks() {
		ArrayList<Bank> allBanks=new ArrayList<>();
		bankRepository.findAll().forEach(bank -> allBanks.add(bank));
		return allBanks;
		}
	
	// Existing method to fetch a bank by ID
	public Optional<Bank> getBankById(Long bankId) {
        return bankRepository.findById(bankId);
    }
	
	// New method to update bank details by ID
    public Bank updateBank(Long bankId, Bank bankDetails) {
        Optional<Bank> existingBank = bankRepository.findById(bankId);
        if (existingBank.isPresent()) {
            Bank bankToUpdate = existingBank.get();
            // Update the fields as necessary
            bankToUpdate.setBankName(bankDetails.getBankName());  // Example of updating the name
            bankToUpdate.setBranchName(bankDetails.getBranchName());  // Example of updating branch
            bankToUpdate.setIfscCode(bankDetails.getIfscCode());  // Example of updating IfscCode
            // Save the updated bank
            return bankRepository.save(bankToUpdate);
        } else {
            // If the bank doesn't exist, return null or throw an exception
            return null; // Or throw a custom exception
        }
    }
 // Method to get a bank by ID
    public Bank getBankById1(Long bankId) {
        return bankRepository.findById(bankId).orElse(null);
    }
    // Method to save the bank (used for both initial creation and updates)
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }
}