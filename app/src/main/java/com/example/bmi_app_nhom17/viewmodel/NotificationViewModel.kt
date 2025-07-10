package com.example.bmi_app_nhom17.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.bmi_app_nhom17.model.NotificationItem

open class NotificationViewModel : ViewModel() {
    var notifications by mutableStateOf(listOf<NotificationItem>())

    init {
        notifications = listOf(
            NotificationItem(1, "New BMI Record", "You just calculated BMI: 22.5", "10:00 AM"),
            NotificationItem(2, "Health Tip", "Drink enough water daily!", "11:30 AM"),
            NotificationItem(3, "Reminder", "Don't forget to log your weight", "12:45 PM")
        )
    }
}