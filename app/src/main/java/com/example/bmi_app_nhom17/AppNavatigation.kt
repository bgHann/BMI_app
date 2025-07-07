package com.example.bmi_app_nhom17

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

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
                onSignup = { navController.navigate("signUp") },
                onforgot = { navController.navigate("sendOtp") },
                onSignin = { navController.navigate("Dashboard") }
            )
        }
        composable("signUp") {
            SignUpScreen(
                onSignInClick = { navController.navigate("signIn") },
                onSignUpClick = { navController.navigate("signIn") }
            )
        }
        composable("sendOtp") {
            Porgot_pass(
                onBackClick = { navController.navigate("signIn") },
                onSendOtpClick = { navController.navigate("enterOtp") }
            )
        }
        composable("enterOtp") {
            EnterOtpScreen(
                onResetClick = { navController.navigate("signIn") }
            )
        }
        composable("Dashboard") {
            Dashboard(
                onBMI = { navController.navigate("BMI") },
                onRight = { navController.navigate("Setting") },
                onleft = { navController.navigate("Track") }
            )
        }
        composable("BMI") {
            BmiCalculatorScreen(
                onBMI = { bmiValue ->
                    navController.navigate("Details/${bmiValue}")
                }
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
                onCenter = { navController.navigate("Dashboard") },
                onRight = { navController.navigate("Setting") }
            )
        }
        composable("Profile") {
            profileSreen(
                onBack = { navController.navigate("Setting") }
            )
        }

        composable(
            route = "Details/{bmi}",
            arguments = listOf(navArgument("bmi") { type = NavType.FloatType })
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            DetailsScreen(
                bmi = bmi,
                onResult = { bmiValue, category ->
                    navController.navigate("Results/$bmiValue/$category")
                }
            )
        }

        composable(
            route = "Results/{bmi}/{category}",
            arguments = listOf(
                navArgument("bmi") { type = NavType.FloatType },
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            ResultsScreen(
                bmi = bmi,
                category = category,
                onHome = {
                    // Từ Results quay lại Dashboard và lưu lịch sử nếu cần
                    navController.navigate("Dashboard")
                }
            )
        }
    }
}
