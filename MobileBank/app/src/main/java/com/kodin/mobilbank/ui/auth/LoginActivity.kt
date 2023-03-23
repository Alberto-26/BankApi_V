package com.kodin.mobilbank.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.kodin.mobilbank.data.db.User
import androidx.lifecycle.Observer

import com.kodin.mobilbank.databinding.ActivityLoginBinding
import com.kodin.mobilbank.model.Login.Login
import com.kodin.mobilbank.ui.home.HomeActivity
import com.kodin.mobilbank.util.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity()  , AuthListener{
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var login: Login?

        viewModel.authListener = this
        viewModel.checkUserTimeLogin()

        binding.buttonSignIn.setOnClickListener {
            val textUser = binding.idUserName.text.toString()
            val textClave = binding.idClave.text.toString()
            login = Login(textUser, textClave)
            viewModel.onLoginButtonClick(login)
        }
        binding.btnQuit.setOnClickListener {

            System.exit(0)

        }

        viewModel.getLoggedInUser()?.observe(this, Observer { user ->

            if (user != null) {
                DataUtil.userStatic =user

                val rolesJson = user.rol.toString()
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    it.putExtra("keyRoles", rolesJson)
                    startActivity(it)
                }
            }
        })


    }



    override fun onStated() {
        binding.progressBar.show()
    }

    override fun onFailure(message: String) {

        binding.progressBar.hide()


        binding.rootLayout.snackbar(message)


    }

    override fun onSuccess(user: User?) {
        binding.progressBar.hide()
    }

    override fun onSuccess(user: String?) {
        binding.progressBar.hide()
        toast(user.toString())
    }
}