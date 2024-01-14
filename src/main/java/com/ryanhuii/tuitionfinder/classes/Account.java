package com.ryanhuii.tuitionfinder.classes;

import org.springframework.stereotype.Component;

public class Account {
    private String uid;
    private String username;
    private String email;
    private String password;
    private String accountType; // within the Parent or Tutor Database, will take the account ID and return the matching object.

    // all-in-one generator for both parent and tutor account. Not sure if i will be using this tho, as the register page has specialisation.
    // when an existing user signs in, they will check in with the accounts database. if match, will return the parent or tutor WITH THE SAME UID
    public Account(String uid, String username, String email, String password, String accountType) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public Account() {}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
