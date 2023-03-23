package com.kodin.mobilbank.data.repositorios

import androidx.lifecycle.LiveData
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.db.UsersDaoGeneral
import com.kodin.mobilbank.data.network.MyApiEndPont
import com.kodin.mobilbank.data.network.SafeApiRequest
import com.kodin.mobilbank.model.Login.Login
import com.kodin.mobilbank.preferences.PreferencesProvider
import com.kodin.mobilbank.util.lazyDeferred
import org.joda.time.Hours
import org.joda.time.Instant
import javax.inject.Inject

class UserRepository       @Inject constructor       (
    private val api: MyApiEndPont,
    private val db: UsersDaoGeneral,
    private val prefs: PreferencesProvider
        ) : SafeApiRequest() {
    private lateinit var user: User


    suspend fun userLogin(login: Login?): User {
        //  return apiRequest { api.userLogin(login) }
        var valorbol = true
        val boleanValor by lazyDeferred {
            valorbol = comprobarTiempo()
        }



        boleanValor.await()
        user = apiRequest {
            api.userLogin(login)
        }
        if (valorbol) {
           // deleteUser(user)
        }
        return user
    }
    suspend fun saveUser(user: User) =db. upsert(user)






    fun getUser(): LiveData<User>? {

        return   db.getuser()
    }

    fun comprobarTiempo(): Boolean {
        val fechaNueva = System.currentTimeMillis()
        val testNow: Instant = Instant.ofEpochMilli(fechaNueva)

        val lastSavedAt = prefs.getLastSavedAt()// <string name="key_saved_at">2021-08-26T09:07:40.734-03:00</string>

        if (lastSavedAt == null) {


            prefs.saveLastSavedAt(testNow.toDateTime().toString())

            return false
        }
        val strparse = Instant.parse(lastSavedAt)

        if ( Hours.hoursBetween( strparse    , testNow ).getHours()  > 6     )       {
            prefs.saveLastSavedAt(        testNow.toDateTime().toString()              )
            return true
        }

        return false
    }

    suspend fun loginDelete() {

        db.deleteUser(db.getuserCom())
        //  db.getUserDao().deleteUserAll()


    }




}