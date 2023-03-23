package com.bankapi.bankapi.Model.jwt;

import java.io.Serializable;
 import java.util.List;

public class TokenInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private  String jwtToken ="Bearer " ;

  private String user;
  private String mail;
  private List<String>  rol;






  public TokenInfo(String jwt, String username, List<String> authorities,String mail) {
    this.jwtToken += jwt;
    this.user = username;
    this.rol = authorities;
    this.mail= mail;


  }


  public String getMail() {
    return mail;
  }

  public String getUser() {
    return user;
  }

  public List<String>  getRol() {
    return rol;
  }

  public String getJwtToken() {
    return this.jwtToken;
  }
}
