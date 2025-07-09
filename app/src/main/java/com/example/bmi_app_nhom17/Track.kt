package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_app_nhom17.data.model.BmiRecord
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor
import com.example.bmi_app_nhom17.viewmodel.bottom_design
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel

@Composable
fun TrackScreen(
    onleft: () -> Unit = {},
    onCenter: () -> Unit = {},
    onRight: () -> Unit = {},
    bmi: Float,
    category: String,
    title: String,
    viewModel: BmiViewModel = viewModel()
) {
    val bmiHistory by viewModel.bmiHistory.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = BackgroudColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Lịch sử 3 lần đo BMI gần nhất:",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier.padding(top = 100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Hiển thị từng lần đo trong lịch sử
            bmiHistory.forEachIndexed { index, record ->
                BmiHistoryItem(record = record, index = index)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            // Thanh điều hướng dưới
            bottom_design(onleft, onCenter, onRight)
        }
    }
}

@Composable
fun BmiHistoryItem(record: BmiRecord, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Lần ${index + 1}",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
            Text(
                text = "BMI: ${String.format("%.1f", record.bmi)}",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6C63FF)
            )
            Text(
                text = "Tình trạng: ${record.category}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(
                text = "Ghi chú: ${record.comment}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
