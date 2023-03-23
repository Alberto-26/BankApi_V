package com.kodin.mobilbank.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kodin.mobilbank.data.db.User


//
fun Context.toast(message: String){
 Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}

fun Context.toast(user: User?){
 Toast.makeText(
  this,
  "el token es :" + user?.mail + " \n rol de usuario es " + user?.user,
  Toast.LENGTH_LONG
 ).show()

}


fun ProgressBar.show() {
 visibility = View.VISIBLE
}


fun ProgressBar.hide() {
 visibility = View.GONE
}



fun View.snackbar(message: String){
 Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->

  snackbar.setAction("Ok") { myView->

   snackbar.dismiss()
  }
 }.show()

}








