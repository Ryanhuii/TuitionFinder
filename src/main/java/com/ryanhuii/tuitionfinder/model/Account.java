package com.ryanhuii.tuitionfinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
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
