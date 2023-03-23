package com.bankapi.bankapi.Model.jwt;

import javax.persistence.*;
@Entity
@Table(name = "UserLogin",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogin;
    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "password", length = 300)
    private String password;
    @Column(name = "role")
    private String role;





    public Long getId() {
        return idLogin;
    }

    public void setId(Long id) {
        this.idLogin = id;
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

    @Override
    public String toString() {
        return "UsuariosLogin{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +

                '}';
    }
}
