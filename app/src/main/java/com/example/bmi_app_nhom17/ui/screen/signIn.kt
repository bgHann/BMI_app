package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.bmi_app_nhom17.model.UserCredentialManager



@Composable
fun signIn(
    onSignin: () -> Unit = {},
    onforgot: () -> Unit = {},
    onSignup: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val passFocusRequester = remember { FocusRequester() }

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val credentialManager = remember { UserCredentialManager(context) }
    val storedPassword = credentialManager.getPassword(email)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }, // chạm ra ngoài để ẩn bàn phím
        color = BackgroudColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 180.dp, start = 25.dp, end = 25.dp)
        ) {
            Text(
                text = "Welcome Back!!",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.size(60.dp))

            RoundedInputField(
                value = email,
                onValueChange = { email = it },
                label = "Enter your email",
                imeAction = ImeAction.Next,
                onImeAction = { passFocusRequester.requestFocus() }
            )

            Spacer(modifier = Modifier.size(30.dp))

            RoundedInputField(
                value = pass,
                onValueChange = { pass = it },
                label = "Enter password",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                focusRequester = passFocusRequester,
                imeAction = ImeAction.Done,
                onImeAction = { focusManager.clearFocus() }
            )

            Spacer(modifier = Modifier.size(30.dp))

            TextButton(onClick = onforgot) {
                Text(
                    text = "Forgot password?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            Button(
                onClick = {
                    val storedPassword = credentialManager.getPassword(email)
                    if (storedPassword != null && pass == storedPassword) {
                        with(sharedPref.edit()) {
                            putString("username", email)
                            apply()
                        }
                        onSignin()
                    } else {
                        Toast.makeText(context, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(83.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF)
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
                                fontWeight = FontWeight.SemiBold,
                                color = BackgroudButon
                            )
                        ) {
                            append("Sign up")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun RoundedInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        shape = RoundedCornerShape(50.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { onImeAction() },
            onDone = { onImeAction() }
        ),
        visualTransformation = visualTransformation,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .let {
                if (focusRequester != null) it.focusRequester(focusRequester) else it
            }
    )
}

@Preview(showBackground = true)
@Composable
fun Previewsingin() {
    signIn()
}
