package com.kodin.mobilbank.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kodin.mobilbank.data.db.RolesTypeConverter
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.data.db.UsersDaoGeneral

@Database(
    entities = [User::class        ],
    version = 4
)

@TypeConverters(RolesTypeConverter::class)
abstract class AppDataBaseHilt : RoomDatabase() {

    abstract fun getFromDataBaseUserDaoTable(): UsersDaoGeneral
}