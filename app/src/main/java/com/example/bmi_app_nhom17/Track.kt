package com.example.bmi_app_nhom17
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmi_app_nhom17.ui.theme.BackgroudColor

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Track : Screen("track")
    object Blank : Screen("blank")
}

@Composable
fun TrackScreen(
    onleft: () -> Unit = {},
    onCenter: () -> Unit = {},
    onRight: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = BackgroudColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            // Top padding như setting
            Spacer(modifier = Modifier.height(130.dp))

            // (Hiện tại không có nội dung giữa màn hình — để trống)
            Spacer(modifier = Modifier.weight(1f))

            // Thanh điều hướng dưới cùng
            bottom_design(onleft, onCenter, onRight)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewTrackScreen() {
    TrackScreen()
}
