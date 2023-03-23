package com.bankapi.bankapi.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserTokenDto {




    @JsonIgnore
    private Long idLogin;

    private String username;

    private String email;



    @JsonIgnore
    private String password;

    private String role;

    public UserTokenDto() {
    }

    public UserTokenDto(Long idLogin, String username, String email, String password, String role) {
        this.idLogin = idLogin;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserTokenDto(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
