package com.ofss.service;
import com.ofss.model.Account;
import com.ofss.model.Transaction;
import com.ofss.model.AccountRepository;
import com.ofss.model.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
//    public List<Transaction> getTransactionsByAccountId(int accountId) {
//        // Example: fetch from DB or repository
//        return transactionRepository.findByAccountId(accountId);
//    }
    @Transactional
    public boolean deposit(int accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null || amount <= 0) {
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType("CREDIT");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription("Deposit");
        transactionRepository.save(transaction);
        return true;
    }
    @Transactional
    public boolean withdraw(int accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null || amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEBIT");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription("Withdrawal");
        transactionRepository.save(transaction);
        return true;
    }
    @Transactional
    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElse(null);
        Account toAccount = accountRepository.findById(toAccountId).orElse(null);
        if (fromAccount == null || toAccount == null || amount <= 0 || fromAccount.getBalance() < amount) {
            return false;
        }
        // Withdraw from source
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);
        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccountNumber(fromAccountId);
        debitTransaction.setAmount(amount);
        debitTransaction.setTransactionType("DEBIT");
        debitTransaction.setTransactionDate(LocalDateTime.now());
        debitTransaction.setDescription("Transfer to account " + toAccountId);
        transactionRepository.save(debitTransaction);
        // Deposit to target
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);
        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccountNumber(toAccountId);
        creditTransaction.setAmount(amount);
        creditTransaction.setTransactionType("CREDIT");
        creditTransaction.setTransactionDate(LocalDateTime.now());
        creditTransaction.setDescription("Transfer from account " + fromAccountId);
        transactionRepository.save(creditTransaction);
        return true;
    }
}