package com.example.bmi_app_nhom17

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor

@Composable
fun Dashboard (
    onBMI : ()->Unit ={},
){
    Surface(modifier = Modifier.fillMaxSize().padding( start = 15.dp, end = 15.dp),
        color = (BackgroudColor)) {
        Column (modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.size(58.dp))
            Text(
                text = "GOOD MORNING",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 35.dp)

                )
            Spacer(modifier = Modifier.size(130.dp))
            Button(
                onClick = onBMI,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFEEEEEE)
                ),
                modifier = Modifier.fillMaxWidth().height(148.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 15.dp,
                   pressedElevation = 25.dp,//khi bamsa
                    hoveredElevation = 20.dp //khi de chuotj qua

                )
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(text = "Calculate your BMI",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        )
                    Spacer(modifier = Modifier.size(10.dp))
                    Image(painter = painterResource(id = R.drawable.dash101771 ),
                        contentDescription = "",
                        modifier = Modifier.height(95.dp)
                        )
                }
            }
            Spacer(modifier = Modifier.size(400.dp))
            bottom_design()

                }
            }

        }


@Preview(showBackground = true)
@Composable
fun DashBoardScreen() {
    Dashboard()
}