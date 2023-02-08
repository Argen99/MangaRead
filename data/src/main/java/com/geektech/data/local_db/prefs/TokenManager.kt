package com.geektech.data.local_db.prefs

import android.annotation.SuppressLint
import android.content.Context

class TokenManager (context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveAccessToken(accessToken: String) {
        val editor = prefs.edit()
        editor.putString(USER_ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken() : String? {
        return prefs.getString(USER_ACCESS_TOKEN, null)
    }

    fun saveRefreshToken(refreshToken: String) {
        val editor = prefs.edit()
        editor.putString(USER_REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getRefreshToken() : String? {
        return prefs.getString(USER_REFRESH_TOKEN, null)
    }

    fun deleteAccessToken() {
        prefs.edit().remove(USER_ACCESS_TOKEN).apply()
    }

    fun deleteRefreshToken() {
        prefs.edit().remove(USER_REFRESH_TOKEN).apply()
    }

    fun clearPrefs() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
        const val USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN"
        const val USER_REFRESH_TOKEN = "USER_REFRESH_TOKEN"
    }
}