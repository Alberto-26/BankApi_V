package com.kodin.mobilbank.ui.UserAcountDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.databinding.FragmentUserAccountDetailsBinding
import com.kodin.mobilbank.model.Transaction.DepositAccount
import com.kodin.mobilbank.model.Transaction.SourceAccount
import com.kodin.mobilbank.model.Transaction.Transaction
import com.kodin.mobilbank.ui.UserAcountDetail.mailmodule.MailSenderApi
import com.kodin.mobilbank.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

private const val TAG = "UserAccountDetails"

@AndroidEntryPoint
class UserAccountDetails : Fragment() {
    private lateinit var accountNumberSource: String
    private lateinit var myemail: String
    private lateinit var mailTicket: String



    private val args by navArgs<UserAccountDetailsArgs>()

    private var _binding: FragmentUserAccountDetailsBinding? = null
    private val binding get() = _binding!!
    private var myRoles: User? = null

    private val viewModel: UserAccountDetailsViewModel by viewModels()


    private val viewModelTransaction: UserAccountTransactionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserAccountDetailsBinding.inflate(inflater, container, false)


        DetallesAccountByUsers()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.idSendFund.setOnClickListener {
            val amountInput = binding.idAmount.text.toString().trim()
            val destinationAccount = binding.idNumberAccount.text.toString().trim()

            if (amountInput.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "you must input your amount...",
                    Toast.LENGTH_SHORT
                ).show()
                binding.root.snackbar("you must input your amount...")
                return@setOnClickListener
            }

            if (destinationAccount.isEmpty()) {
                binding.root.snackbar("you must input destination account...")
                Toast.makeText(
                    requireContext(),
                    "you must input destination account...",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
             mailTicket = binding.idMailAccount.text.toString().trim()

            var sourceAccount = SourceAccount(accountNumberSource.toInt())


            var depositAccount = DepositAccount(destinationAccount.toInt())


            var transaction = Transaction(amountInput.toDouble(), depositAccount, sourceAccount)
            Coroutines.main {
                val transactionInfo =
                    viewModelTransaction.transactionInfo(DataUtil.userStatic, transaction)

                /*  viewModelTransaction.transactionInfo(   DataUtil.userStatic ,transaction).observe(viewLifecycleOwner,
                  Observer { cadena->
                      Log.d(TAG, "onViewCreated: cadena devuelta es  : "+cadena)
                  })*/

                if (!mailTicket.isEmpty()) {
                    MailSenderApi(
                        myemail,
                        mailTicket,
                        transactionInfo
                    ).sendMailHtml()
                }


                Log.d(TAG, "onViewCreated: " + transactionInfo)

                binding.root.snackbar("transfer sent successfully")
            }

        }
    }

    fun myRoleFun() = runBlocking {
        if (myRoles == null) {
            Coroutines.main {
                runBlocking {
                    myRoles = viewModel.getUserRol()

                    DetallesAccountByUsers()
                }
            }
        }

    }

    private fun DetallesAccountByUsers() {

        Coroutines.main {

            try {
                viewModel.showDetailAccount(DataUtil.userStatic, args.getUserDetail).await()
                    ?.observe(requireParentFragment().viewLifecycleOwner,
                        Observer { lista ->
                            binding.progressfBar.hide()

                            accountNumberSource = lista.accountNumber.toString()

                             myemail = lista.user.email
                            binding.idTitle.text = buildString {
                                append("\nWelcome user " + lista.user.name + " " + lista.user.lastname)
                                append("\n Your balance : " + lista.balance)
                                append("\n your Account Number : " + lista.accountNumber)
                                append("\nUser id : ")
                                append(lista.user.userId)
                                append("\nYour mail User : \n" + lista.user.email)


                            }


                        })

            } catch (e: ApiException) {
                Toast.makeText(requireContext(), " error tipo " + e.message, Toast.LENGTH_LONG)
                    .show()

            } catch (e: NoInternetException) {
                Toast.makeText(requireContext(), " error tipo " + e.message, Toast.LENGTH_LONG)
                    .show()


            }


        }


    }


}