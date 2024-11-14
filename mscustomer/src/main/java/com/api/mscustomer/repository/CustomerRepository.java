package com.api.mscustomer.repository;

import org.springframework.stereotype.Repository;
import com.api.mscustomer.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Optional<Customer> findByDocument(@Param("document") String document);
}
