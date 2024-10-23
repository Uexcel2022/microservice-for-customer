package com.uexcel.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Wallet extends BaseEntity {
    @Id
    private Long id;
    private Long customerId;
    private Long balance;
}
