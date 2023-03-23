package com.bankapi.bankapi.Model;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "userId", unique=true, nullable=false)
    private Integer userId;
    private String name;
    private String lastname;


    private String password;
    private String email;
    private String celphoneNumber;
    

}
