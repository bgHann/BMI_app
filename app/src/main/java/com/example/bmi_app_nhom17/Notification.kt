package com.example.bmi_app_nhom17

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


// Dữ liệu mẫu cho mỗi thông báo
data class NotificationItem(
    val id: Int,
    val titles: String,
    val message: String,
    val time: String
)

// Giao diện chính hiển thị danh sách thông báo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    notifications: List<NotificationItem>,
    onBack: () -> Unit = {},
    onClickItem: (NotificationItem) -> Unit = {}
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
            // Duyệt qua từng phần tử trong danh sách để tạo thẻ thông báo
            items(notifications, key = { it.id }) { notification ->
                NotificationCard(notification, onClick = { onClickItem(notification) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// Giao diện mỗi thẻ thông báo
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

// Xem thử trước trong Android Studio
@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    val sampleNotifications = listOf(
        NotificationItem(1, "Chỉ số BMI mới nhất", "Bạn vừa tính BMI: 22.5", "04/07/2025 10:00"),
        NotificationItem(2, "Lời khuyên sức khỏe", "Uống đủ nước mỗi ngày!", "03/07/2025 18:00")
    )

    NotificationScreen(
        notifications = sampleNotifications
    )
}
