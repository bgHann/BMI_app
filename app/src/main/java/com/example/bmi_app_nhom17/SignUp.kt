package com.example.bmi_app_nhom17

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    onSignInClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize().background(Color(0xfffD9D9D9))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp, start = 18.dp, end = 18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome Onboard!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                text = "Let's help you meet your goal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Full Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name", modifier = Modifier.padding(start = 40.dp)) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),



            )

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", modifier = Modifier.padding(start = 40.dp)) },
                shape = RoundedCornerShape(50.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", modifier = Modifier.padding(start = 40.dp)) },
                shape = RoundedCornerShape(50.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = {
                    Text(
                        "Confirm passwork",
                        modifier = Modifier.padding(start = 40.dp)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF)
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text("Sign Up", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onSignInClick) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        ) {
                            append("Already have an account? ")
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


