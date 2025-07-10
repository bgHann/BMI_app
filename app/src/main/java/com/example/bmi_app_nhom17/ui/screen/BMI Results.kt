package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel

@Composable
fun BmiResultScreen(
    bmi: Float,
    category: String,
    onDetails: (Float) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "BMI Result",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            BmiCircularIndicator(bmi = bmi)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                buildAnnotatedString {
                    append("You have ")
                    withStyle(style = SpanStyle(color = Color(0xFF6C63FF), fontWeight = FontWeight.Bold)) {
                        append("$category")
                    }
                    append(" body weight!")
                },
                color = Color.Black,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {onDetails(bmi)},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(24.dp), clip = false)
            ) {
                Text(
                    text = "Details",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BmiCircularIndicator(bmi: Float) {
    val percentage = bmi / 40f
    val sweepAngle = percentage * 360f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(180.dp)
            .shadow(10.dp, shape = CircleShape, ambientColor = Color.Gray)
    ) {
        Canvas(modifier = Modifier.size(180.dp)) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )

            drawArc(
                color = Color(0xFF6C63FF),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )
        }

        Text(
            text = String.format("%.1f", bmi),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun BmiResultPreview(){
    BmiResultScreen(
        bmi = 23.2f,
        category = "Normal",
        onDetails = {},
    )
}
