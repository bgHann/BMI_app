package com.example.bmi_app_nhom17

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Track() {
    val history = listOf(
        BmiRecord("02/07/2025", 60f, 170f),
        BmiRecord("30/06/2025", 63f, 170f),
        BmiRecord("28/06/2025", 59f, 170f)
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Lịch sử BMI", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(history) { record ->
                BmiHistoryItem(record)
                Divider()
            }
        }
    }
}

data class BmiRecord(val date: String, val weight: Float, val height: Float)

@Composable
fun BmiHistoryItem(record: BmiRecord) {
    val bmi = record.weight / ((record.height / 100) * (record.height / 100))
    val status = when {
        bmi < 18.5 -> "Gầy"
        bmi < 25 -> "Bình thường"
        bmi < 30 -> "Thừa cân"
        else -> "Béo phì"
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Text("Ngày: ${record.date}")
        Text("Cân nặng: ${record.weight} kg")
        Text("Chiều cao: ${record.height} cm")
        Text("BMI: %.1f - %s".format(bmi, status))
    }
}
