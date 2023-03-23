package com.kodin.mobilbank.data.repositorios

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.network.MyApiEndPont
import com.kodin.mobilbank.data.network.SafeApiRequest
import com.kodin.mobilbank.model.AccountbyUser.UserAccountStatus
import com.kodin.mobilbank.model.Transaction.Transaction
import javax.inject.Inject

private const val TAG = "TransactionRepository"
class TransactionRepository      @Inject constructor(
    private val api: MyApiEndPont
) : SafeApiRequest() {
    private val infoTransaction = MutableLiveData<String>()




    suspend fun sendTransaction(myToken: User ,transaction: Transaction) : String {

        var apiRequest: String? = null
        try {

                Log.d(TAG, "sendTransaction: token es " +myToken+ " transaction json es "+transaction.toString())

             //   val apiRequest = api.sendFoundTransaction(myToken.jwtToken, transaction)

            apiRequest = apiRequest {


                api.sendFoundTransaction(myToken.jwtToken, transaction)

            }



        } catch (e: Exception) {
            Log.d(TAG, "sendTransaction: "+e.message)

        }
       // infoTransaction.postValue(apiRequest!!)
        return apiRequest!!
    }


}