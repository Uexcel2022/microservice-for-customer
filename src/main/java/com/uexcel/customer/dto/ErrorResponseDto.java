package com.uexcel.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ErrorResponseDto {
    private int status;
    private String error;
    private String apiPath;
    private String timestamp;
}
