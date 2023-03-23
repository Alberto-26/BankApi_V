package com.kodin.mobilbank.model.userDetailsBank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UsersDetailsItem(
    val celphoneNumber: String,
    val email: String,
    val lastname: String,
    val name: String,
    val password: String,
    val userId: Int
) : Parcelable