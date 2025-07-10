package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor

@Composable
fun EnterOtpScreen(onResetClick: () -> Unit = {}) {
    // State cho các ô nhập
    var otp by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Surface(modifier = Modifier.fillMaxSize(),
        color = (BackgroudColor)) {
        Column(
            modifier = Modifier.clickable { focusManager.clearFocus() }
                .fillMaxSize()
                .padding(top = 110.dp, start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Input OTP",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Nhập OTP
            OutlinedTextField(
                value = otp,
                onValueChange = { otp = it },
                placeholder = { Text("Enter the OTP", modifier = Modifier.padding(start = 15.dp)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))

            // Text phụ
            Text(
                text = "We have sent an OTP to your registered email",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))

            Spacer(modifier = Modifier.height(180.dp))

            Button(
                onClick = onResetClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BackgroudButon),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp
                )
            ) {
                Text(
                    text = "Reset Password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun EnterOtpPreview(){
    EnterOtpScreen()
}