package com.ofss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ofss.model.Account;
import com.ofss.model.AccountRepository;
import com.ofss.model.Customer;
import com.ofss.model.CustomerRepository;
import com.ofss.model.Bank;
import com.ofss.model.BankRepository;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BankRepository bankRepository;

    // List all accounts
    public Iterable<Account> listAccounts() {
        return accountRepository.findAll();
    }

    // Get a single account by ID, along with customer and bank details
    public ResponseEntity<Object> getAccountById(int accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(account.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }

    // Add a new account
    public ResponseEntity<Object> addAccount(Account account) {
        Customer customer = account.getCustomer();
        Bank bank = account.getBank();
        if (customer != null && bank != null) {
            accountRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer or Bank information missing");
        }
    }

    // Delete an account by ID
    public ResponseEntity<Object> deleteAccountById(int accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            accountRepository.deleteById(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }

    // Update account details
    public ResponseEntity<Object> updateAccountById(int accountNumber, Account updatedAccount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            Account existingAccount = account.get();
            existingAccount.setAccountCreationDate(updatedAccount.getAccountCreationDate());
            existingAccount.setAccountType(updatedAccount.getAccountType());
            existingAccount.setBalance(updatedAccount.getBalance());
            // Removed the ifscCode update
            existingAccount.setCustomer(updatedAccount.getCustomer());
            existingAccount.setBank(updatedAccount.getBank());
            accountRepository.save(existingAccount);
            return ResponseEntity.status(HttpStatus.OK).body("Account updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }

    // Update account details partially (e.g., updating balance only)
    public ResponseEntity<Object> updateAccountPartially(int accountNumber, Account partialAccount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            Account existingAccount = account.get();
            if (partialAccount.getAccountType() != null) {
                existingAccount.setAccountType(partialAccount.getAccountType());
            }
            if (partialAccount.getBalance() != 0) {
                existingAccount.setBalance(partialAccount.getBalance());
            }
            // Removed the ifscCode update logic
            accountRepository.save(existingAccount);
            return ResponseEntity.status(HttpStatus.OK).body("Account partially updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
    }
}
