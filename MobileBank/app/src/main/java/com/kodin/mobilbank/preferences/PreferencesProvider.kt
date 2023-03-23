package com.kodin.mobilbank.preferences


import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_SAVED_AT = "key_saved_at"


@Singleton
class PreferencesProvider @Inject constructor (@ApplicationContext context: Context)


{

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String) {
        preferences.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastSavedAt(): String? {
        return preferences.getString(KEY_SAVED_AT, null)
    }


}