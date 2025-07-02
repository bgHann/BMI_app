package com.example.bmi_app_nhom17

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor
import androidx.compose.ui.platform.LocalContext
import android.content.Context


@Composable
fun profileSreen(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val name = sharedPref.getString("Name", null)
    val savedEmail = sharedPref.getString("email", null)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroudColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, end = 25.dp, start = 25.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.left),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = "Profile",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(250.dp))

            // Username title
            Text(
                text = "Username:",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            infor("$name")
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Email Address:",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            infor("$savedEmail")
            }
        }
    }



@Preview(showBackground = true)
@Composable
fun PreviewprofileScreen(){
    profileSreen()
}
@Composable
fun infor (
    title : String
){
androidx.compose.material3.Card(
modifier = Modifier
.fillMaxWidth()
.height(53.dp),
colors = CardDefaults.cardColors(
containerColor = Color(0xFFFFFFFF),
),
    shape = RoundedCornerShape(40.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}
}

