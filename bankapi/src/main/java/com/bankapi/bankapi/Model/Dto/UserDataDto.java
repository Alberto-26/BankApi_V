package com.bankapi.bankapi.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "userId", "name", "username", "roles", "email" })
public class UserDataDto {
    private Integer userId;
    private String name;

    private String roles;
    private String lastname;

    private String username;

    @JsonIgnore
    private String password;
    private String email;
    private String celphoneNumber;

    public UserDataDto() {
    }

    public UserDataDto(Integer userId, String name,String roles, String lastname, String username,String password, String email, String celphoneNumber) {
        this.userId = userId;
        this.name = name;
        this.roles =roles;
        this.lastname = lastname;
        this.username =username;
        this.password = password;
        this.email = email;
        this.celphoneNumber = celphoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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

    public String getCelphoneNumber() {
        return celphoneNumber;
    }

    public void setCelphoneNumber(String celphoneNumber) {
        this.celphoneNumber = celphoneNumber;
    }
}
