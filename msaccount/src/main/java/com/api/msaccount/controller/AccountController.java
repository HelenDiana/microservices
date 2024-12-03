package com.api.msaccount.controller;

import com.api.msaccount.dto.MoneyRequest;
import com.api.msaccount.model.Account;
import com.api.msaccount.service.AccountService;
import com.api.msaccount.enums.AccountType;
import com.api.msaccount.strategy.WithdrawalStrategy;
import com.api.msaccount.strategy.WithdrawalStrategyFactory;
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
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/accounts")
    public ResponseEntity<Map<String, Object>> getAccounts(){
        List<Account> accounts = accountService.listAccounts();
        Map<String, Object> response = new HashMap<>();
        response.put("data", accounts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccountById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", account);
        if (account.isPresent()) {
          return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<Map<String, Object>> createAccount(@RequestBody Account accountBody) {
        Account _account = accountService.saveAccount(accountBody);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cuenta creada exitosamente");
        response.put("data", _account);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/accounts/{id}/deposit")
    public ResponseEntity<Map<String, Object>> despositAccount(@PathVariable int id ,@RequestBody MoneyRequest moneyRequest) {
        Optional<Account> accountExist = accountService.getAccountById(id);
        if (accountExist.isPresent()) {
          Map<String, Object> response = new HashMap<>();
          Account _account = accountExist.get();
          _account.setBalance(_account.getBalance() + moneyRequest.getMoney());
          response.put("message", "Se ha depositado dinero correctamente");
          response.put("data", accountService.saveAccount(_account));
          return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/accounts/{id}/withdraw")
    public ResponseEntity<Map<String, Object>> withdrawAccount(@PathVariable int id ,@RequestBody MoneyRequest moneyRequest) {
        Optional<Account> accountExist = accountService.getAccountById(id);
        if (accountExist.isPresent()) {
            Account _account = accountExist.get();
            WithdrawalStrategy strategy = WithdrawalStrategyFactory.getStrategy(_account.getAccountType());

            Map<String, Object> response = new HashMap<>();
            if (!strategy.canWithdraw(_account.getBalance(), moneyRequest.getMoney())) {
                response.put("message", strategy.getErrorMessage());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            _account.setBalance(_account.getBalance() - moneyRequest.getMoney());
            response.put("message", "Se ha retirado dinero correctamente");
            response.put("data", accountService.saveAccount(_account));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") int id) {
        Optional<Account> accountExist = accountService.getAccountById(id);
        if (accountExist.isPresent()) {
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
