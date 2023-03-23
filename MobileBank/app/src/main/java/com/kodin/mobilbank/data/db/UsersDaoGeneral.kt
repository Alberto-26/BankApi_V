package com.kodin.mobilbank.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDaoGeneral {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long





    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>




    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    suspend fun getuserCom() : User


    @Query("DELETE  FROM User" )
    suspend fun deleteUserAll( )


    @Delete
    suspend fun deleteUser(user: User)

}