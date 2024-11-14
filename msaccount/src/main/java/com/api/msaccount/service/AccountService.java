package com.api.msaccount.service;

import com.api.msaccount.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {    
    public List<Account> listAccounts();
    public Optional<Account> getAccountById(int id);
    public Account saveAccount(Account client);
    public void deleteAccount(int id);
}
