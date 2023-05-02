package com.jospehsun.springbootmall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UserRegisterRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Date createdDate;
    private Date lastModifiedDate;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
