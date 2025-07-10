package com.example.bmi_app_nhom17.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bmi_app_nhom17.model.BmiRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BmiViewModel : ViewModel() {
    var height by mutableStateOf(170f) // ✅ dùng by mutableStateOf để UI cập nhật
    var weight by mutableStateOf(70)

    private val _bmiHistory = MutableStateFlow<List<BmiRecord>>(emptyList())
    val bmiHistory = _bmiHistory.asStateFlow()

    fun calculateBmi(): Float {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    fun getCategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }

    fun getComment(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Try to eat more nutritious meals and consult a nutritionist."
            bmi < 24.9 -> "Maintain your current lifestyle with balanced diet and regular exercise."
            bmi < 29.9 -> "Consider increasing physical activity and reducing high-calorie foods."
            else ->"Consult a doctor or dietitian to create a safe weight loss plan."
        }
    }

    fun addBmiRecord(bmi: Float, category: String, comment: String) {
        val newRecord = BmiRecord(bmi, category, comment)
        _bmiHistory.value = listOf(newRecord) + _bmiHistory.value.take(2)
    }

    // Có thể thêm hàm điều chỉnh weight nếu cần
    fun increaseWeight() {
        weight++
    }

    fun decreaseWeight() {
        if (weight > 1) weight--
    }
}
