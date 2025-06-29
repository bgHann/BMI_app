package com.example.bmi_app_nhom17

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun signIn (
    onSignin : () -> Unit = {},
    onforgot : () -> Unit = {},
    onSignup : () -> Unit = {}) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 180.dp, start = 25.dp , end = 25.dp)
        ) {
            Text(
                text = "Welcome Back!!",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(60.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Enter your email") },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            )
            Spacer(modifier = Modifier.size(60.dp))
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                placeholder = { Text("Enter passwork") },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            TextButton(
                onClick = onforgot,
            ) {
                Text(
                    text = "Forgot password?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick = onSignin,
                modifier = Modifier.fillMaxWidth().height(83.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF),
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                )
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 23.sp
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            TextButton(onClick = onSignup) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        ) {
                            append("Don't have an account? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Sign in")
                        }
                    }
                )


            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun Previewsingin() {
    signIn()
}
