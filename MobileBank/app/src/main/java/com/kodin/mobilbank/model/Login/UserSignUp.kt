package com.kodin.mobilbank.model.Login

data class UserSignUp(
    val email: String,
    val password: String,
    val role: List<String>?,
    val username: String
)

/*
  "username" : "admin",
    "email" : "admin@sebas.com",
    "password" :"123456",
    "role" : "ADMIN"
 */