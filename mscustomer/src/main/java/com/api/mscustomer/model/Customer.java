package com.api.mscustomer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty(message = "FirstName required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "LastName required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Document required")
    @Column(name = "document", unique = true)
    private String document;
    
    @NotEmpty(message = "Email required")
    @Email(message = "Email not valid format")
    @Column(name = "email")
    private String email;
    
}
