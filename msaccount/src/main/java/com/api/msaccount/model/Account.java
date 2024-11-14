package com.api.msaccount.model;

import com.api.msaccount.enums.AccountType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "balance", nullable = false, columnDefinition = "DECIMAL(15,2)")
    private double balance = 0.00;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('AHORRO','CORRIENTE')")
    private AccountType accountType;
    
    @Column(name = "id_customer", nullable = false)
    private int idCustomer;
    
    @PrePersist
    public void generateUniqueCode() {
        this.accountNumber = UUID.randomUUID().toString();
    }
}
