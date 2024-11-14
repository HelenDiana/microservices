package com.api.mscustomer.service.impl;

import com.api.mscustomer.model.Customer;
import com.api.mscustomer.dto.ApiResponseDTO;
import com.api.mscustomer.dto.AccountResponseDTO;
import com.api.mscustomer.repository.CustomerRepository;
import com.api.mscustomer.service.CustomerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public List<Customer> listCustomers() {
      return customerRepository.findAll();
    }
  
    @Override
    public Optional<Customer> getCustomerById(int id) {
      return customerRepository.findById(id);
    }
    
    @Override
    public Optional<Customer> getCustomerByDocument(String document) {
      return customerRepository.findByDocument(document);
    }
    
    @Override
    public Customer saveCustomer(Customer customer) {
      return customerRepository.save(customer);
    }
    
    @Override
    public Boolean validateAccountsByIdCustomer(int id) {
        ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity("http://localhost:8081/api/accounts", ApiResponseDTO.class);
        List<AccountResponseDTO> accounts = response.getBody().getData();
        return accounts.stream().anyMatch(account -> account.getIdCustomer() == id);
    }
    
    @Override
    public void deleteCustomer(int id) {
      customerRepository.deleteById(id);
    }
}
