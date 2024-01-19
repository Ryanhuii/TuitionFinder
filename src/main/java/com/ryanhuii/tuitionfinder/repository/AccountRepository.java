package com.ryanhuii.tuitionfinder.repository;

import com.ryanhuii.tuitionfinder.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    public Account findAccountByUsernameAndPassword(String username, String password);
}
