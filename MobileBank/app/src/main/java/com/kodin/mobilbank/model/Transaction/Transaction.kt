package com.kodin.mobilbank.model.Transaction

data class Transaction(
    val amount: Double,
    val depositAccount: DepositAccount,
    val sourceAccount: SourceAccount
)