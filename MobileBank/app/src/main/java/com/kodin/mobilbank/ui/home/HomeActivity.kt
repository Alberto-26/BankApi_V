package com.kodin.mobilbank.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.kodin.mobilbank.R
import com.kodin.mobilbank.ui.auth.AuthViewModel
import com.kodin.mobilbank.util.Coroutines
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeActivity"
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModels()

    private var toolbarHome: Toolbar? = null
    private var navigationView: NavigationView? = null
    private lateinit var navController: NavController
    private lateinit var drawerLayout  : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle: Bundle? = intent.extras
        val jsonRol = bundle!!.getString("keyRoles") // VIENE DE LOGIN ACTIVITY





        toolbarHome = findViewById(R.id.toolbarHome)
        navigationView = findViewById(R.id.menu_nav_view)
        drawerLayout =    findViewById(R.id.drawer_layout)



        setSupportActionBar(toolbarHome)








        if(jsonRol.equals("[[ROLE_USER]]")){
          //  navigationView!!.menu.findItem(R.id.UserAccountDetails).isVisible = false

        }

        if(jsonRol.equals("[[ROLE_ADMIN]]")){

        }
        navController = Navigation.findNavController(this, R.id.fragmentHome)

        navigationView!!.menu.findItem(R.id.logout).setOnMenuItemClickListener { suMennu->

            when (suMennu.itemId){
                R.id.logout -> {
                    Log.d(TAG, "onCreate: ")

                    Toast.makeText(this, ".....", Toast.LENGTH_SHORT).show()
                    Coroutines.main {
                        viewModel.logOut()

                        exitApp()
                    }



                    true
                }
                else -> false
            }
        }



     /*   navigationView?.setNavigationItemSelectedListener { suMennu->

            when (suMennu.itemId){
                R.id.logout -> {
                    Log.d(TAG, "onCreate: ")
                    viewModel.logOut()

                    true
                }
                else -> false

            }
        }*/

    /*    navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logout -> {
                    viewModel.logOut()
                    true
                }
                else -> {
                    false
                }
            }
        }*/

        NavigationUI.setupWithNavController(navigationView!!, navController!!)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)



    }

    private fun exitApp() {
      System.exit(0)
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragmentHome),
            drawerLayout
        )
    }



  /*  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                Log.d(TAG, "onOptionsItemSelected: ")
                true
            }

            R.id.usersDetailsFragment -> {
                Log.d(TAG, "onOptionsItemSelected: ")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }*/



}