package com.example.bmi_app_nhom17.model

import android.content.Context

class LocalOtpManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("otp_prefs", Context.MODE_PRIVATE)

    fun saveOtp(email: String, otp: String) {
        prefs.edit().putString("otp_$email", otp).apply()
    }

    fun getOtp(email: String): String? {
        return prefs.getString("otp_$email", null)
    }

    fun clearOtp(email: String) {
        prefs.edit().remove("otp_$email").apply()
    }
}