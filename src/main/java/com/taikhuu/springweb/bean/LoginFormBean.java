package com.taikhuu.springweb.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginFormBean {
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
