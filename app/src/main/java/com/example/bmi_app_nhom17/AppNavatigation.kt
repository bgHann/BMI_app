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
                onStartClick = { navController.navigate("signIn") }
            )
        }
        composable("signIn") {
            signIn(
                onSignup = { navController.navigate("sign up") },
                onforgot = { navController.navigate("gửi mã otp") },
                onSignin = { navController.navigate("Dashboard") }
            )
        }
        composable("signUp") {
            SignUpScreen(
                onSignInClick = { navController.navigate("quay lại signIn") },
                onSignUpClick = { navController.navigate("signIn") }
                )
        }
        composable("sign up") {
            SignUpScreen(
                onSignInClick = { navController.navigate("quay lại signIn") },
                onSignUpClick = { navController.navigate("signIn") }
            )
        }
        composable("gửi mã otp") {
            Porgot_pass(
                onBackClick = { navController.navigate("quay lại màn signIn") },
                onSendOtpClick = { navController.navigate("Enter OTP") }

            )
        }
        composable("quay lại màn signIN") {
            signIn(
                onforgot = { navController.navigate("gửi mã otp") },
                onSignup = { navController.navigate("quay lại sign up") },
                onSignin = { navController.navigate("Dashboard") }

            )
        }
        composable("Enter OTP") {
            EnterOtpScreen(
                onResetClick = { navController.navigate("signIn") }
            )
        }
        composable("Dashboard") {
            Dashboard(
                onBMI = { navController.navigate("BMI") },
                onRight = { navController.navigate("Setting") },
                onleft = { navController.navigate("Track")}
            )
        }
        composable("BMI") {
            BmiCalculatorScreen(
                onBMI = {navController.navigate("Bmi_Results")}
            )
        }
        composable("Setting") {
            settingScreen(
                onCenter = { navController.navigate("Dashboard") },
                onPrileclick = { navController.navigate("Profile") },
                onSignOut = { navController.navigate("signIn") }
            )

        }
        composable("Track") {
            TrackScreen(
                onleft = { navController.navigate("Dashboard") },
                onCenter = { navController.navigate("DashBoard") },
                onRight = { navController.navigate("Setting")}
            )
        }
        composable("Profile") {
            profileSreen(
                onBack = { navController.navigate("Setting") }
                )
        }
        composable("Bmi_results"){
            BmiResultScreen(
                onDetails = {navController.navigate("Details")}
            )
        }
        composable("Details"){
        }

    }
}

