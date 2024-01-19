package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// The standard I'm setting for collection names is just the caps version of the class name, and in plural form. I don't think it would cause any issues Java-wise
@Document(collection = "Accounts")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id // for mongo, need to manually generate the id from our side.
    private String uid;

    private String username;
    private String email;
    private String password;
    private String accountType; // within the Parent or Tutor Database, will take the account ID and return the matching object.

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

}
