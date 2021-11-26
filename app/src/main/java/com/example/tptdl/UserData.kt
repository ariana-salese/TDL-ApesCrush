package com.example.tptdl

import android.content.Context
import android.content.SharedPreferences

class UserData(val context: Context) {

    private val SHARED_NAME = "UserData"
    private val SHARED_LEVEL = "level"

    private val storage: SharedPreferences = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveLastAvailableLevel(level: Int) {
        storage.edit().putInt(SHARED_LEVEL, level).apply()
    }

    fun getLastAvailableLevel() : Int {
        return storage.getInt(SHARED_LEVEL, 1)
    }
}