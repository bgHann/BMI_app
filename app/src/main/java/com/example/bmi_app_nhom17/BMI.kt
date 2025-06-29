package com.example.bmi_app_nhom17


import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// SUửa lại
@Composable
fun BmiCalculatorScreen() {
    var isMale by remember { mutableStateOf(true) }
    var height by remember { mutableStateOf(155f) }
    var weight by remember { mutableStateOf(65) }
    var age by remember { mutableStateOf(24) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("BMI CALCULATOR", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Malecart("Woman", R.drawable.femenine, !isMale) { isMale = false }
                Malecart("Man", R.drawable.femenine, isMale) { isMale = true }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("HEIGHT", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("${height.toInt()} cm", fontSize = 32.sp, color = Color(0xFF6C63FF))
                    Slider(
                        value = height,
                        onValueChange = { height = it },
                        valueRange = 100f..220f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF6C63FF),
                            activeTrackColor = Color.Black
                        )
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ValueCard(
                    label = "WEIGHT",
                    value = weight,
                    onIncrease = { weight++ },
                    onDecrease = { if (weight > 1) weight-- }
                )
                Spacer(modifier = Modifier.width(8.dp))
                ValueCard(
                    label = "AGE",
                    value = age,
                    onIncrease = { age++ },
                    onDecrease = { if (age > 1) age-- }
                )
            }

            Button(
                onClick = { /* handle calculate BMI */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Calculate BMI", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun Malecart(
    label: String,
    @DrawableRes icon: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) Color(0xFFEDE7F6) else Color(0xFFF2F2F2)
    val borderColor = if (selected) Color(0xFF6C63FF) else Color.Transparent

    Column(
        modifier = Modifier
//            .weight(1f)
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, borderColor),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = if (selected) Color(0xFF6C63FF) else Color.Gray,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun ValueCard(label: String, value: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Card(
        modifier = Modifier
//            .weight(1f)
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(label, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("$value", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircleButton("+") { onIncrease() }
                CircleButton("-") { onDecrease() }
            }
        }
    }
}

@Composable
fun CircleButton(text: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .background(Color(0xFF6C63FF), CircleShape)
            .clickable { onClick() }
    ) {
        Text(text, color = Color.White, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BmiCalculatorScreenPreview() {
    BmiCalculatorScreen()
}