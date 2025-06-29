package com.example.bmi_app_nhom17

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "frame1") {
        composable("frame1") {
            frame1(
                onNextClick = { navController.navigate("welcome") }
            )
        }
        composable("welcome") {
            WelcomeScreen(
                onStartClick = {navController.navigate("signUp")}
            )
        }
        composable("signUp"){
            SignUpScreen(
                onSignInClick = {navController.navigate("signIn")},
                onSignUpClick = {navController.navigate("")}
            )
        }
        composable("signIn"){
            signIn(
                onSignup = {navController.navigate("quay lại sign up")},
                onforgot = {navController.navigate("gửi mã otp")}
            )

        }
        composable("quay lại sign up"){
            SignUpScreen(
                onSignInClick = {navController.navigate("signIn")}

            )
        }
        composable("gửi mã otp"){
            SendOtpScreen(
                onBackClick = {navController.navigate("quay lại màn signIn")}
            )

        }
        composable("quay lại màn signIN"){
            signIn(
                onforgot = {navController.navigate("gửi mã otp")},
                onSignup = {navController.navigate("quay lại sign up")}

            )
        }

    }}

