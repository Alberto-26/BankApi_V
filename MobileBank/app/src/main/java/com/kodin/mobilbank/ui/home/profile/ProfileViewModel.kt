package com.kodin.mobilbank.ui.home.profile

import androidx.lifecycle.ViewModel
import com.kodin.mobilbank.data.repositorios.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel          @Inject constructor (

    private val  repository: UserRepository
        )        : ViewModel() {
    val user = repository.getUser()
}