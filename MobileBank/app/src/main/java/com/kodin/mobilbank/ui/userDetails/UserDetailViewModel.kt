package com.kodin.mobilbank.ui.userDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.repositorios.UserDetailRepository
import com.kodin.mobilbank.data.repositorios.UserRepository
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import com.kodin.mobilbank.util.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel  @Inject constructor              (

    private val repository: UserDetailRepository

        )       : ViewModel() {
    var userErr: ErrApiRest? = null

    suspend fun getUserRol() :User?  {
        return  repository.getUserComun()
    }

     suspend  fun obtenerListaRoles(myUser: User?) : Deferred<LiveData<List<UsersDetailsItem>>>? {
         var   myListas : Deferred<LiveData<List<UsersDetailsItem>>>?     =null
         try{

         /*    if (myUser!!.rol.toString() .equals("[ROLE_USER]")){
                 val myListaUser by lazyDeferred {
                     repository.getProductosRol(myUser)
                 }
                 myListas =myListaUser
                 return myListas

             }*/

             val myListaUser by lazyDeferred {
                 repository.getProductosRol(myUser!!)
             }
             myListas =myListaUser
             return myListas
         }catch (e:Exception){
             userErr?.errorUser(e)
             return null
         }
         return myListas
     }



}