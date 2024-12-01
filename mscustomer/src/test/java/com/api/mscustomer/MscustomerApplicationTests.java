package com.api.mscustomer;

import com.api.mscustomer.model.Customer;
import com.api.mscustomer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MscustomerApplicationTests {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);       
    }

    @Test
    public void testObtenerCustomerPorId() {
        Optional<Customer> customer = customerServiceImpl.getCustomerById(2);
        assertTrue(customer.isPresent());
        assertEquals("Roberto", customer.get().getFirstName());
    }

    @Test
    public void testAllCustomers() {
        List<Customer> customers = customerServiceImpl.listCustomers();
        assertNotNull(customers);
        //assertEquals(3, customers.size());
        assertEquals("Marcos", customers.get(0).getFirstName());
    }
    
    @Test
    public void testGuardarCustomer() {
        Customer _customer = new Customer();
        _customer.setDocument("123456");
        _customer.setEmail("test@gmail.com");
        _customer.setFirstName("Test");
        _customer.setLastName("Test2");
        Customer customerSave = customerServiceImpl.saveCustomer(_customer);
        assertNotNull(customerSave);
        assertEquals("Test", customerSave.getFirstName());
    }
}
