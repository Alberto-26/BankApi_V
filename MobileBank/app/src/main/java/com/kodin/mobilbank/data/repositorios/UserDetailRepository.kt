package com.kodin.mobilbank.data.repositorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.db.UsersDaoGeneral
import com.kodin.mobilbank.data.network.MyApiEndPont
import com.kodin.mobilbank.data.network.SafeApiRequest
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailRepository @Inject constructor          (
    private val api: MyApiEndPont,
    private val db: UsersDaoGeneral

        ): SafeApiRequest() {
    private val userList = MutableLiveData<List<UsersDetailsItem>>()
    suspend fun getUserComun(): User? {

        //  return db.getUserDao().getuserCom()
        return db.getuserCom()

    }

    suspend fun getProductosRol(myUser: User): LiveData<List<UsersDetailsItem>> {
        return withContext(Dispatchers.IO) {
            obtenerUsersForLiveDataRol(myUser)
        }

    }

    private suspend fun obtenerUsersForLiveDataRol(myUser: User): LiveData<List<UsersDetailsItem>> {
        var response: List<UsersDetailsItem>? = null
        response = apiRequest {
           api.adminItems(myUser.jwtToken)

        }
           userList.postValue(response!!)
        return userList
    }





}