package com.ofss.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "TRANSACTION_ID_SEQ", allocationSize = 1)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private int accountNumber;
    @Column(name = "AMOUNT", nullable = false)
    private double amount;
    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType; // "DEBIT" or "CREDIT"
    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDateTime transactionDate;
    @Column(name = "DESCRIPTION")
    private String description;
    public Transaction() {}
    public Transaction(int accountNumber, double amount, String transactionType, LocalDateTime transactionDate, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
    }
    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}//transaction.java