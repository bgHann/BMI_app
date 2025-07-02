package com.example.bmi_app_nhom17

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor

@Composable
fun settingScreen (
    onPrileclick : () -> Unit ={ },
    onHistory : ()-> Unit = {},
    onSignOut : ()-> Unit = {},
    onleft : ()->Unit = {},
    onCenter : ()->Unit = {},
    onRight: ()->Unit = {},
) {
    Surface(modifier = Modifier.fillMaxSize(), color = BackgroudColor) {
        Column(modifier = Modifier.fillMaxSize().padding(start = 15.dp, end = 15.dp)) {


            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 130.dp, start = 15.dp, end = 15.dp)
            ) {
                Card(title = "Profile", onclick = onPrileclick)
                Spacer(modifier = Modifier.size(15.dp))
                Card(title = "History", onclick = onHistory)
                Spacer(modifier = Modifier.size(15.dp))
                Card(title = "Sign Out", onclick = onPrileclick)
                Spacer(modifier = Modifier.size(380.dp))
            }
            bottom_design(onleft,onCenter,onRight)
        }
    }
    }

@Preview(showBackground = true)
@Composable
fun PreviewSetting() {
    settingScreen()
}
@Composable
fun Card (
    onclick : ()-> Unit,
    title : String){
    Card(
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier.fillMaxWidth().height(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6C63FF).copy(0.7f),
        )

    ) {
        Row (modifier = Modifier.fillMaxSize().padding(start = 40.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = title ,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton (onClick = onclick,
                modifier = Modifier.size(46.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.image),
                    contentDescription = "",
                )

            }
        }
    }
}