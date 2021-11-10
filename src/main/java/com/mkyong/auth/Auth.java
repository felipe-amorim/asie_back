package com.mkyong.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Auth {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a user")
    private String user;

    @NotEmpty(message = "Please provide a password")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
