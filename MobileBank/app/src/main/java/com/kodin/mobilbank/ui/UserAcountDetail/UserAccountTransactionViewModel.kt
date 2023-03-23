package com.kodin.mobilbank.ui.UserAcountDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.repositorios.TransactionRepository
import com.kodin.mobilbank.model.Transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import javax.inject.Inject


@HiltViewModel
class UserAccountTransactionViewModel    @Inject constructor              (
    private val repository : TransactionRepository
        ) : ViewModel()          {
    suspend  fun transactionInfo(myUser: User?,transaction: Transaction) : String {

        val sendTransaction = repository.sendTransaction(myUser!!, transaction)

        return sendTransaction

    }
    //: Deferred<LiveData<UserAccountStatus>?> {

}