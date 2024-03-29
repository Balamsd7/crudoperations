package com.balamsd7.flightbooking.dto;

import lombok.NonNull;

import java.io.Serializable;

public class LoginRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull
    private String userName;
    @NonNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
