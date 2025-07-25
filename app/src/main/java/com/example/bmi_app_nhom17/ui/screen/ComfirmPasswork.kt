package com.example.bmi_app_nhom17.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor
import androidx.compose.ui.platform.LocalContext
import com.example.bmi_app_nhom17.model.UserCredentialManager

@Composable
fun ComfirmPassworkScreen(
    email: String = "",
    onComfirmClick: () -> Unit = {}
) {
    // State cho các ô nhập
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val credentialManager = remember { UserCredentialManager(context) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = (BackgroudColor)
    ) {
        Column(
            modifier = Modifier.clickable { focusManager.clearFocus() }
                .fillMaxSize()
                .padding(top = 110.dp, start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Comfirm Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            // Nhập mật khẩu mới
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                placeholder = {
                    Text(
                        "Enter new password",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))

// Nhập lại mật khẩu
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = {
                    Text(
                        "Confirm new password",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp)
            )
            Spacer(modifier = Modifier.height(180.dp))

            Button(
                onClick = {
                    if (newPassword.length < 6) {
                        Toast.makeText(context, "Mật khẩu phải dài ít nhất 6 ký tự", Toast.LENGTH_SHORT).show()
                    } else if (newPassword != confirmPassword) {
                        Toast.makeText(context, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                    } else {
                        credentialManager.savePassword(email, newPassword)
                        Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show()
                        onComfirmClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BackgroudButon)
            ) {
                Text(
                    text = "Comfirm",
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
fun ComfirmPreview(){
    ComfirmPassworkScreen()
}