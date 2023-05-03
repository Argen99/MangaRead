package com.geektech.data.local_db.prefs

import android.content.Context

class SelectedItemsPrefs(context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_CHECKBOX, Context.MODE_PRIVATE)

    fun saveState(position: Int, isChecked: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean("$SELECTED_ITEM$position", isChecked)
        editor.apply()
    }

    fun getState(position: Int): Boolean {
        return prefs.getBoolean("$SELECTED_ITEM$position", false)
    }

    fun clearPrefs() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val PREFS_CHECKBOX = "PREFS_CHECKBOX"
        const val SELECTED_ITEM = "SELECTED_ITEM"
    }
}