package com.example.bmi_app_nhom17.model

object OtpUtil {
    fun generateOtp(): String {
        return (100000..999999).random().toString()
    }
}
