package com.ofss.controller;

import com.ofss.model.Account;
import com.ofss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Get all accounts
    @GetMapping
    public Iterable<Account> listAllAccounts() {
        return accountService.listAccounts();
    }

    // Get an account by account number (includes customer and bank)
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getAccountById(@PathVariable int accountNumber) {
        return accountService.getAccountById(accountNumber);
    }

    // Add a new account
    @PostMapping("/add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    // Delete an account by account number
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Object> deleteAccount(@PathVariable int accountNumber) {
        return accountService.deleteAccountById(accountNumber);
    }

    // Update account completely
    @PutMapping("/{accountNumber}")
    public ResponseEntity<Object> updateAccount(@PathVariable int accountNumber, @RequestBody Account account) {
        return accountService.updateAccountById(accountNumber, account);
    }

    // Partially update account
    @PatchMapping("/{accountNumber}")
    public ResponseEntity<Object> updateAccountPartially(@PathVariable int accountNumber, @RequestBody Account account) {
        return accountService.updateAccountPartially(accountNumber, account);
    }
}
