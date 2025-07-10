package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_app_nhom17.model.NotificationItem
import com.example.bmi_app_nhom17.viewmodel.NotificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = viewModel(),
    onBack: () -> Unit = {},
    onClickItem: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.notifications, key = { it.id }) { notification ->
                NotificationCard(notification, onClick = { onClickItem(notification.message) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = notification.titles, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = notification.time, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    val sampleNotifications = listOf(
        NotificationItem(1, "Chỉ số BMI mới nhất", "Bạn vừa tính BMI: 22.5", "04/07/2025 10:00"),
        NotificationItem(2, "Lời khuyên sức khỏe", "Uống đủ nước mỗi ngày!", "03/07/2025 18:00")
    )
    val text = object : NotificationViewModel() {
        init { notifications = sampleNotifications }
    }
    NotificationScreen(viewModel = text)
}
