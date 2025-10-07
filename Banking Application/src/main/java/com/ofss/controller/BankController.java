package com.ofss.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ofss.model.Bank;
import com.ofss.service.BankService;
@RestController
public class BankController {
    @Autowired
    private BankService bankService;
    // API to add a bank
    // POST http://localhost:8184/banks
    @PostMapping("/banks")
    public ResponseEntity<Object> addABank(@RequestBody Bank bank) {
        return bankService.addABank(bank);
    }
    // API 2 to list all the banks
    @GetMapping("/banks")
    public List<Bank> listAllBanks() {
    return bankService.listBanks();
    }
 // New API to get a bank by its ID
    @GetMapping("/banks/id/{bankId}")
    public Optional<Bank> getBankById(@PathVariable Long bankId) {
        return bankService.getBankById(bankId);
    }
 // New API to update a bank by its ID
    @PutMapping("/banks/id/{bankId}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long bankId, @RequestBody Bank bankDetails) {
        Bank updatedBank = bankService.updateBank(bankId, bankDetails);
        if (updatedBank != null) {
            return ResponseEntity.ok(updatedBank); // Return 200 OK with the updated bank
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if bank not found
        }
    }
    @PatchMapping("/banks/id/{bankId}")
    public ResponseEntity<Bank> partialUpdateBank(@PathVariable Long bankId, @RequestBody Map<String, Object> updates) {
        // Fetch the bank to be updated
        Bank existingBank = bankService.getBankById1(bankId);
        if (existingBank == null) {
            return ResponseEntity.notFound().build();
        }
        // Apply the updates to the existing bank
        updates.forEach((key, value) -> {
            switch (key) {
                case "bankName":
                    existingBank.setBankName((String) value);
                    break;
                case "branchName":
                    existingBank.setBranchName((String) value);
                    break;
                case "ifscCode":
                    existingBank.setIfscCode((String) value);
                    break;
            }
        });
        // Save the updated bank to the repository
        Bank updatedBank = bankService.saveBank(existingBank);
        return ResponseEntity.ok(updatedBank);
    }
}