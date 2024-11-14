package com.api.msaccount.service.impl;

import com.api.msaccount.model.Account;
import com.api.msaccount.repository.AccountRepository;
import com.api.msaccount.service.AccountService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public List<Account> listAccounts() {
      return accountRepository.findAll();
    }
  
    @Override
    public Optional<Account> getAccountById(int id) {
      return accountRepository.findById(id);
    }
    
    @Override
    public Account saveAccount(Account account) {
      return accountRepository.save(account);
    }
    
    @Override
    public void deleteAccount(int id) {
      accountRepository.deleteById(id);
    }
    
}
