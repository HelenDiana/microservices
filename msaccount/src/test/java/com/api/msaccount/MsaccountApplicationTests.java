package com.api.msaccount;


import com.api.msaccount.enums.AccountType;
import com.api.msaccount.model.Account;
import com.api.msaccount.service.impl.AccountServiceImpl;
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
class MsaccountApplicationTests {

    /*@Test
    void contextLoads() {
    }*/
    
    @Autowired
    private AccountServiceImpl accountServiceImpl;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);       
    }

    @Test
    public void testObtenerAccountPorId() {
        Optional<Account> account = accountServiceImpl.getAccountById(1);
        assertTrue(account.isPresent());
    }

    @Test
    public void testAllAccounts() {
        List<Account> accounts = accountServiceImpl.listAccounts();
        assertNotNull(accounts);
    }
    
    @Test
    public void testGuardarAccount() {
        Account _account = new Account();
        _account.setAccountType(AccountType.CORRIENTE);
        _account.setBalance(3800);
        _account.setId((long)1123);        
        Account accountSave = accountServiceImpl.saveAccount(_account);
        assertNotNull(accountSave);
    }

}
