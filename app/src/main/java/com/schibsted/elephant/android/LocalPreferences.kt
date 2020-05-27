package com.schibsted.elephant.android

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class LocalPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("intaaction", Context.MODE_PRIVATE)

    fun saveUUID(uuid: String) {
        preferences.edit { putString("uuid", uuid) }
    }

    fun getUUID(): String = preferences.getString("uuid", "").orEmpty()
}