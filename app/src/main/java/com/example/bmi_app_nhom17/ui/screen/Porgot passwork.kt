package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor

@Composable
fun Porgot_pass(
    onBackClick: () -> Unit = {},
    onSendOtpClick: () -> Unit = {}, )
{
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf(TextFieldValue("")) }
    Surface(modifier = Modifier.fillMaxSize()
        .clickable {focusManager.clearFocus() }, color = BackgroudColor) {
        Column(modifier = Modifier.padding(top = 25.dp, start = 20.dp
        , end = 20.dp)) {
            IconButton(onBackClick) {
                Icon(

            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 6.dp)
                .size(28.dp)
                .clickable { onBackClick() }
                )
        }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()

                    .padding(24.dp)

            ) {


                Spacer(modifier = Modifier.height(36.dp))

                Text(
                    text = "Let’s get you back in",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Email input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Enter your email") },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "       We’re going to send an OTP to your\n                        registered email",
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.size(350.dp))

                // Send OTP button
                Button(
                    onClick = onSendOtpClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(83.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroudButon
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp
                    )
                ) {
                    Text(
                        text = "Send OTP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSendOtpScreen() {
   Porgot_pass()
}
