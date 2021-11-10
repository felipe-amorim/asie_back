package com.mkyong.session;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please provide a session uuid")
    private String session;

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }
}
