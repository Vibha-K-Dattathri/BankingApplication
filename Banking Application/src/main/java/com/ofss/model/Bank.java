package com.ofss.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BANK") // maps to your Oracle table BANK
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // works well with Oracle 11g+ identity column
    @Column(name = "BANK_ID")
    private Long bankId;

    @Column(name = "BANK_NAME", nullable = false, length = 200)
    private String bankName;

    @Column(name = "BRANCH_NAME", length = 200)
    private String branchName;

    @Column(name = "IFSC_CODE", nullable = false, unique = true, length = 20)
    private String ifscCode;

    // Default constructor required by JPA
    public Bank() {}

    // Parameterized constructor
    public Bank(Long bankId, String bankName, String branchName, String ifscCode) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
    }

    // Getters and Setters
    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}
