package com.example.bmi_app_nhom17.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.bmi_app_nhom17.model.BmiData

class BmiViewModel : ViewModel() {
    var height by mutableStateOf(155f)
    var weight by mutableStateOf(65)
    var bmiResult by mutableStateOf<BmiData?>(null)


    fun increaseWeight() {
        weight++
    }

    fun decreaseWeight() {
        if (weight > 1) weight--
    }

    fun calculateBmi(): Float {
        val bmi = weight / ((height / 100) * (height / 100))
        bmiResult = BmiData(
            weight = weight,
            height = height,
            bmi = bmi
        )
        return bmi
    }
}
