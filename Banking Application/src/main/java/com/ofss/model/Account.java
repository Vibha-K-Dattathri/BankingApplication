package com.ofss.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @Column(name="account_number")
    private int accountNumber;
    
    @Column(nullable = false)
    private LocalDate accountCreationDate;
    
    private String accountType;
    
    private double balance;
    
    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
    
    // Constructors, Getters, Setters
    public Account() {
    }
    
    // Updated constructor without 'ifscCode'
    public Account(LocalDate accountCreationDate, String accountType, double balance, Customer customer, Bank bank) {
        this.accountCreationDate = accountCreationDate;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
        this.bank = bank;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }
    
    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Bank getBank() {
        return bank;
    }
    
    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
