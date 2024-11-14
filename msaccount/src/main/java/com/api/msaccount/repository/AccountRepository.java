package com.api.msaccount.repository;

import org.springframework.stereotype.Repository;
import com.api.msaccount.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    
}
