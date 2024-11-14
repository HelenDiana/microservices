package com.api.mscustomer.controller;

import com.api.mscustomer.model.Customer;
import com.api.mscustomer.service.CustomerService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
 
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/customers")
    public ResponseEntity<Map<String, Object>> getCustomers(){
        List<Customer> customers = customerService.listCustomers();
        Map<String, Object> response = new HashMap<>();
        response.put("data", customers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/customers/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerById(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", customer);
        if (customer.isPresent()) {
          return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Map<String, Object>> createCustomer(@Valid @RequestBody  Customer customerBody) {
        Optional<Customer> customerExist = customerService.getCustomerByDocument(customerBody.getDocument());
        Map<String, Object> response = new HashMap<>();
        if (customerExist.isPresent()) {
            response.put("message", "Customere ya existe");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        Customer _customer = customerService.saveCustomer(customerBody);
        response.put("message", "Customere creado exitosamente");
        response.put("data", _customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable int id ,@Valid @RequestBody Customer customerBody) {
        Optional<Customer> customerExist = customerService.getCustomerById(id);
        if (customerExist.isPresent()) {
          Map<String, Object> response = new HashMap<>();
          Customer _customer = customerExist.get();
          _customer.setFirstName(customerBody.getFirstName());
          _customer.setLastName(customerBody.getLastName());
          _customer.setDocument(customerBody.getDocument());
          _customer.setEmail(customerBody.getEmail());
          response.put("message", "Customere actualizado exitosamente");
          response.put("data", customerService.saveCustomer(_customer));
          return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable("id") int id) {
        Optional<Customer> customerExist = customerService.getCustomerById(id);
        Boolean customerHasAccountsActive = customerService.validateAccountsByIdCustomer(id);
        if (customerExist.isPresent()) {
            if(customerHasAccountsActive){
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Cliente tiene cuentas activas");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }else{
                customerService.deleteCustomer(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
