package com.kodin.mobilbank.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.repositorios.UserRepository
import com.kodin.mobilbank.model.Login.Login
import com.kodin.mobilbank.util.ApiException
import com.kodin.mobilbank.util.Coroutines
import com.kodin.mobilbank.util.DataUtil
import com.kodin.mobilbank.util.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor ( private val repository: UserRepository)   : ViewModel() {


    var user: User? = null




    var authListener: AuthListener? = null


    fun getLoggedInUser(): LiveData<User>? {
        return repository.getUser()
    }

    fun checkUserTimeLogin() {
        val usertime = repository.comprobarTiempo()
        if (!usertime) return         // es falso se loguea por primera vez
        if (usertime) {    // es verdadero se necesita borrar tabla usuario supero las 6 horas
            runBlocking {
                repository.loginDelete()
            }

        }
    }

    suspend fun logOut(){

            //  db.getUserDao().deleteUserAll()
            repository.loginDelete()




    }

    fun onLoginButtonClick(login: Login?) {

        authListener?.onStated()

        if (login!!.usuario.isNullOrEmpty() || login.clave.isNullOrEmpty()) {
            authListener?.onFailure("invalid email or password")
            return
        }


        Coroutines.main {

            try {
                val authResponse = repository.userLogin(login!!)
                    DataUtil.userStatic =authResponse
                authResponse.jwtToken.let { datos ->

                    authListener?.onSuccess("login success")

                    repository.saveUser(authResponse)
                    return@main

                 }

            }catch (e: ApiException) {

                authListener?.onFailure(e.message!!)

            }catch (e: NoInternetException) {
                //    FirebaseCrashlytics.getInstance().recordException(e)

                authListener?.onFailure(e.message!!)
            }


        }

    }






}