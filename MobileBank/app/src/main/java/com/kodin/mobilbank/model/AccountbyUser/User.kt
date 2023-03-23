package com.kodin.mobilbank.model.AccountbyUser

data class User(
    val celphoneNumber: String,
    val email: String,
    val lastname: String,
    val name: String,
    val password: String,
    val userId: Int
)