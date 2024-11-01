package com.uexcel.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uexcel.customer.entity.Wallet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    @JsonIgnore
    private String password;

    private Wallet wallet;
}
