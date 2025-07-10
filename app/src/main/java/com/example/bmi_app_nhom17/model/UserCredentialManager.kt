package com.example.bmi_app_nhom17.model

import android.content.Context

class UserCredentialManager(context: Context) {
    private val prefs = context.getSharedPreferences("user_cred", Context.MODE_PRIVATE)

    fun savePassword(email: String, newPassword: String) {
        prefs.edit().putString("password_$email", newPassword).apply()
    }

    fun getPassword(email: String): String? {
        return prefs.getString("password_$email", null)
    }
}