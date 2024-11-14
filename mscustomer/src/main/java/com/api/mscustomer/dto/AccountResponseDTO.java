package com.api.mscustomer.dto;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private double balance;
    private String accountType;
    private int idCustomer;   
}
