package com.mohsinkd786.dtos;

import java.util.UUID;

public class UserDto {

    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
