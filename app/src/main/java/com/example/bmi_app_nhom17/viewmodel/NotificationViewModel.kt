package com.example.bmi_app_nhom17.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.bmi_app_nhom17.data.model.NotificationItem

open class NotificationViewModel : ViewModel() {
    var notifications by mutableStateOf(listOf<NotificationItem>())

    init {
        notifications = listOf(
            NotificationItem(1, "Chúc mừng!", "Bạn vừa đạt chỉ số BMI lý tưởng!", "Hôm nay"),
            NotificationItem(2, "Lời nhắc", "Đừng quên đo lại BMI vào tuần sau nhé.", "Hôm qua")
        )
    }
}