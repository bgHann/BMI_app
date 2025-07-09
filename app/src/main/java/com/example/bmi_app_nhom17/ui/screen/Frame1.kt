package com.example.bmi_app_nhom17.ui.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_app_nhom17.ui.theme.BackgroudButon


@Composable
fun frame1(onNextClick : ()-> Unit){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xfffD9D9D9)),
        ){
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "WELCOME",
                modifier = Modifier.padding(top = 150.dp),
                fontSize = 48.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .padding(top= 400.dp)
                    .width(301.dp)    //   ngang
                    .height(81.dp),   //  cao
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroudButon

                ),
                elevation = ButtonDefaults.buttonElevation( // Bóng đổ
                    defaultElevation = 10.dp,//độ cao mặc định khi chưa click
                    pressedElevation = 15.dp,// độ cao khi click
                    disabledElevation = 0.dp,//vô hiệu hóa khi disble
                    hoveredElevation = 100.dp//khhi di chuột qua
                )


                ) {
                Text(text = "Next",
                    fontSize =23.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

}
