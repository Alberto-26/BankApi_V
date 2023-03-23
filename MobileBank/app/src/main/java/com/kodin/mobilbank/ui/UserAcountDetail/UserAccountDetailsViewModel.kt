package com.kodin.mobilbank.ui.UserAcountDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.repositorios.UserAccountDetailRepository
import com.kodin.mobilbank.model.AccountbyUser.UserAccountStatus
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import com.kodin.mobilbank.util.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import javax.inject.Inject

@HiltViewModel
class UserAccountDetailsViewModel  @Inject constructor              (
    private val repository : UserAccountDetailRepository

        ): ViewModel() {


    suspend fun getUserRol() : User?  {
        return  repository.getUserComun()
    }



    suspend  fun showDetailAccount(myUser: User?, userDetail: UsersDetailsItem) : Deferred<LiveData<UserAccountStatus>?> {

     var   userStatusAccount: Deferred<LiveData<UserAccountStatus>?>? =null


     try {
         val accountUser by lazyDeferred {
             repository.showDetailUserAcconut(myUser!!,userDetail)
         }
         userStatusAccount = accountUser
     }catch (e:Exception){
         Log.d("TAG", "showDetailAccount: "+e.message)

     }


        return userStatusAccount!!
    }

}