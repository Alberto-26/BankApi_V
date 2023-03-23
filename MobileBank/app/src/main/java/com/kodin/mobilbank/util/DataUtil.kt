package com.kodin.mobilbank.util

import android.widget.ProgressBar
import android.view.View
import com.kodin.mobilbank.data.db.User

import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem

class DataUtil {
    companion object {
        public var listUsersStatic: MutableList<UsersDetailsItem> = mutableListOf()

        var userStatic: User? =null
    }




}