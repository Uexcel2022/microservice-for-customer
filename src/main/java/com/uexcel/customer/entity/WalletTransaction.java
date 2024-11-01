package com.uexcel.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private long walletId;
    private String accountNumber;
    private String accountDescription;
    private String transactionType;
    private double amount;
}
