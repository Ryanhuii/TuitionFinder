package com.ryanhuii.tuitionfinder.service;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    // Login
    public Account loginAccount(String username, String password) {
        return repository.findAccountByUsernameAndPassword(username,password);
    }

    // Create
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    // Read
    public Account getAccountByUid(String uid) {
        return repository.findById(uid).get();
    }

    // Update - I doubt that I would touch this but I'll just add it in here
    // Same for delete account functionality.
    // For now, I will ignore the PUT and DELETE mappings, and only focus on CREATE and READ functions.
    //public Account updateAccount(Account account) {}
    //public Account deleteAccount(Account account) {}
}
