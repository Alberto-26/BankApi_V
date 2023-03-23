package com.kodin.mobilbank.data.repositorios

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.db.UsersDaoGeneral
import com.kodin.mobilbank.data.network.MyApiEndPont
import com.kodin.mobilbank.data.network.SafeApiRequest
import com.kodin.mobilbank.model.AccountbyUser.UserAccountStatus
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import javax.inject.Inject

class UserAccountDetailRepository @Inject constructor(

    private val api: MyApiEndPont,
    private val db: UsersDaoGeneral
) : SafeApiRequest() {
    private val userAccountStatus = MutableLiveData<UserAccountStatus>()


    suspend fun showDetailUserAcconut(myUser: User, userDetail: UsersDetailsItem): LiveData<UserAccountStatus>? {
        val numero = "2"
        var apiRequest: UserAccountStatus? = null
        try {
            apiRequest = apiRequest {


                api.getDetailAccountUser(myUser.jwtToken, userDetail.userId.toString())

            }
            //  met(apiRequest) //  abstract fun met(apiRequest: UserAccountStatus)
        } catch (e: Exception) {

            Log.d("TAG", "showDetailUserAcconut: "+e.message)
            return null
        }

        userAccountStatus.postValue(apiRequest!!)
        return userAccountStatus

    }
    suspend fun getUserComun(): User? {

        //  return db.getUserDao().getuserCom()
        return db.getuserCom()

    }

}