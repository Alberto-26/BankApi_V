package com.kodin.mobilbank.data.network

import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.model.AccountbyUser.UserAccountStatus
import com.kodin.mobilbank.model.Login.Login
import com.kodin.mobilbank.model.Transaction.Transaction
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import retrofit2.Response
import retrofit2.http.*


interface MyApiEndPont {
    //http://localhost:8080/authenticate/register
    @POST("authenticate")
    suspend fun userLogin(@Body login: Login?): Response<User>


//http://localhost:8080/user

    @GET("user")
    suspend fun adminItems(@Header("Authorization") autToken: String): Response<List<UsersDetailsItem>>


    //http://localhost:8080/account/accountbyuser/3
    @GET("account/accountbyuser/{id}")
    suspend fun getDetailAccountUser(
        @Header("Authorization") autToken: String,
        @Path("id") id: String
    ): Response<UserAccountStatus>

    @POST("transaction") //http://localhost:8080/transaction
    suspend fun sendFoundTransaction(@Header("Authorization") autToken: String ,@Body transaction: Transaction  ) : Response< String>


    /*
       @PUT("api/test/mod/items/ventas/{id}")      //@PutMapping("/mod/items/ventas/{id}")
    suspend fun updateItemsInsertMod(
        @Header("Authorization") autToken: String,
        @Path("id") id: String,
        @Body items: Items
    ): Response<Items>
     */


}