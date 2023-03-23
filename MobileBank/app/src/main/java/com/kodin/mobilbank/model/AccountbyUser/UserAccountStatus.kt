package com.kodin.mobilbank.model.AccountbyUser

data class UserAccountStatus(
    val accountNumber: Int,
    val balance: Double,
     val type: Type,
     val user: User
)