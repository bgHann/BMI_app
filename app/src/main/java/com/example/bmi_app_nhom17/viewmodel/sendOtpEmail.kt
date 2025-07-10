package com.example.bmi_app_nhom17.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun sendOtpEmail(context: Context, email: String, otp: String) {
    val subject = "Mã OTP xác thực"
    val message = "Mã OTP của bạn là: $otp"

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // chỉ mở ứng dụng gửi email
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
    }

    try {
        context.startActivity(Intent.createChooser(intent, "Chọn ứng dụng gửi OTP"))
    } catch (e: Exception) {
        Toast.makeText(context, "Không có ứng dụng gửi email", Toast.LENGTH_SHORT).show()
    }
}