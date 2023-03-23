package com.kodin.mobilbank.data.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

const val CURRENT_USER_ID = 0

@Entity
@Parcelize
data class User(

    val id: Int,
    val user: String,
    val mail: String,


    val rol: MutableList<String>,

     val jwtToken: String,

) : Parcelable {

    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID

}

class RolesTypeConverter {
    @TypeConverter
    fun fromString(value: String?): MutableList<String> {
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: MutableList<String>): String {
        return Gson().toJson(list)
    }

}