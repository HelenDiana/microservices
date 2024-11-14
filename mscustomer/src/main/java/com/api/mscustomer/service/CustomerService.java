package com.api.mscustomer.service;

import com.api.mscustomer.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> listCustomers();
    public Optional<Customer> getCustomerById(int id);
    public Optional<Customer> getCustomerByDocument(String document);
    public Customer saveCustomer(Customer Customer);
    public Boolean validateAccountsByIdCustomer(int id);
    public void deleteCustomer(int id);
}
