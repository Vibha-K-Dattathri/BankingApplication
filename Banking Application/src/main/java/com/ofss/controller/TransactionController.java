package com.ofss.controller;
import com.ofss.model.Transaction;
import com.ofss.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
//    @GetMapping("/transactions/accounts/{accountId}")
//    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int accountId) {
//        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
//        if (transactions == null || transactions.isEmpty()) {
//            return ResponseEntity.noContent().build(); // 204 No Content if no transactions found
//        }
//        return ResponseEntity.ok(transactions);
//    }
    // Deposit API
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> deposit(@PathVariable int accountId, @RequestBody Map<String, Object> payload) {
        Integer toDestinationAccount = (Integer) payload.get("toDestinationAccount");
        Double depositAmount = Double.valueOf(payload.get("depositAmount").toString());
        if (toDestinationAccount == null || depositAmount == null || depositAmount <= 0) {
            return ResponseEntity.badRequest().body("Invalid deposit data");
        }
        boolean success = transactionService.deposit(toDestinationAccount, depositAmount);
        if (success) {
            return ResponseEntity.ok("Deposit successful");
        } else {
            return ResponseEntity.badRequest().body("Deposit failed");
        }
    }
    // Withdraw API
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<String> withdraw(@PathVariable int accountId, @RequestBody Map<String, Object> payload) {
        Double withdrawAmount = Double.valueOf(payload.get("withdrawAmount").toString());
        if (withdrawAmount == null || withdrawAmount <= 0) {
            return ResponseEntity.badRequest().body("Invalid withdraw amount");
        }
        boolean success = transactionService.withdraw(accountId, withdrawAmount);
        if (success) {
            return ResponseEntity.ok("Withdrawal successful");
        } else {
            return ResponseEntity.badRequest().body("Withdrawal failed");
        }
    }
    // Transfer API
    @PostMapping("/transfer/{fromSourceAccount}/{toTargetAccount}")
    public ResponseEntity<String> transfer(@RequestBody Map<String, Object> payload) {
        Integer fromSourceAccount = (Integer) payload.get("fromSourceAccount");
        Integer toTargetAccount = (Integer) payload.get("toTargetAccount");
        Double transferAmount = Double.valueOf(payload.get("transferAmount").toString());
        if (fromSourceAccount == null || toTargetAccount == null || transferAmount == null || transferAmount <= 0) {
            return ResponseEntity.badRequest().body("Invalid transfer data");
        }
        boolean success = transactionService.transfer(fromSourceAccount, toTargetAccount, transferAmount);
        if (success) {
            return ResponseEntity.ok("Transfer successful");
        } else {
            return ResponseEntity.badRequest().body("Transfer failed");
        }
    }
}