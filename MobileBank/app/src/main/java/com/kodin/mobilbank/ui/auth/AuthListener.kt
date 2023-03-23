package com.kodin.mobilbank.ui.auth

import com.kodin.mobilbank.data.db.User

interface AuthListener {
    fun onStated()
    fun onFailure(message: String)
    fun onSuccess(user: User?)
    fun onSuccess(user: String?)
}