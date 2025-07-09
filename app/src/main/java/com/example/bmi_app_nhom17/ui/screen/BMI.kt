package com.example.bmi_app_nhom17.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_app_nhom17.R
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel

@Composable
fun BmiCalculatorScreen(
    viewModel: BmiViewModel = viewModel(),
    onBMI: (Float) -> Unit
) {
    var isMale by remember { mutableStateOf(true) }
    val height = viewModel.height
    val weight = viewModel.weight
    var age by remember { mutableStateOf(24) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BMI CALCULATOR",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF6C63FF), RoundedCornerShape(12.dp))
                    .padding(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Malecart("Woman", R.drawable.femenine, !isMale) { isMale = false }
                    Malecart("Man", R.drawable.masculine, isMale) { isMale = true }
                }
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
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontSize = 32.sp, color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                                append("${height.toInt()}")
                            }
                            withStyle(SpanStyle(fontSize = 16.sp, color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                                append(" cm")
                            }
                        }
                    )
                    Slider(
                        value = height,
                        onValueChange = { viewModel.height = it },
                        valueRange = 100f..220f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF6C63FF),
                            activeTrackColor = Color(0xFF6C63FF),
                            inactiveTrackColor = Color.LightGray
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
                    onIncrease = { viewModel.increaseWeight() },
                    onDecrease = { viewModel.decreaseWeight() },
                    plusIcon = R.drawable.plus,
                    minusIcon = R.drawable.minus,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ValueCard(
                    label = "AGE",
                    value = age,
                    onIncrease = { age++ },
                    onDecrease = { if (age > 1) age-- },
                    plusIcon = R.drawable.plus,
                    minusIcon = R.drawable.minus,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                onClick = {
                    val bmi = viewModel.calculateBmi()
                    onBMI(bmi)
                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
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
    val backgroundColor = if (selected) Color(0xFFF5F5F5) else Color(0xFFF2F2F2)
    val borderColor = if (selected) Color(0xFF00B0FF) else Color.Transparent

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // KHÔNG chồng icon lên card bên trong nữa → tránh tạo khung vuông xám
        Card(
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(2.dp, borderColor),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = Color.Unspecified, // giữ nguyên màu gradient gốc
                    modifier = Modifier.size(80.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Chỉ giữ 1 dòng text chính
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}



@Composable
fun ValueCard(
    label: String,
    value: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    @DrawableRes plusIcon: Int,
    @DrawableRes minusIcon: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
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
                CircleButton(icon = plusIcon) { onIncrease() }
                CircleButton(icon = minusIcon) { onDecrease() }
            }
        }
    }
}

@Composable
fun CircleButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF6C63FF), CircleShape)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiCalculatorScreenPreview() {
    BmiCalculatorScreen(onBMI = {})
}
