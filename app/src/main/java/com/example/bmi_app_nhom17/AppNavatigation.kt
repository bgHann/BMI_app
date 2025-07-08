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
                backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            val title = backStackEntry.arguments?.getString("title") ?: "title"
            Dashboard(
                onBMI = { navController.navigate("BMI") },
                onRight = { navController.navigate("Setting") },
                onleft = { navController.navigate("Tracks/${bmi}/${category}/${title}") }
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
                onSignOut = { navController.navigate("signIn") },
                onNotifications = {navController.navigate("Notification")},
                onleft = {navController.navigate("Tracks")}
            )
        }

        composable(
            route = "Tracks/{bmi}/{category}/{title}",
            arguments = listOf(
                navArgument("bmi") { type = NavType.FloatType },
                navArgument("category") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType}
            )
        )
        {backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            val title = backStackEntry.arguments?.getString("title") ?: "title"
            TrackScreen(
                bmi = bmi,
                category = category,
                title = title,
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

        composable("Notification") {
            val dummyNotifications = listOf(
                NotificationItem(1, "Thông báo 1", "Bạn có tin nhắn mới", "10:00 AM"),
                NotificationItem(2, "Thông báo 2", "Cập nhật hệ thống", "11:30 AM"),
                NotificationItem(3, "Thông báo 3", "Sự kiện sắp diễn ra", "12:45 PM")
            )
            NotificationScreen(
                notifications = dummyNotifications,
                onBack = {
                    navController.popBackStack()
                },
                onClickItem = { notification ->
                    navController.navigate(
                        "NotificationDetail/${notification.titles}/${notification.message}/${notification.time}"
                    )
                }
            )
        }

        composable(
            "NotificationDetail/{titles}/{message}/{time}",
            arguments = listOf(
                navArgument("titles") { type = NavType.StringType },
                navArgument("message") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val titles = backStackEntry.arguments?.getString("titles") ?: ""
            val message = backStackEntry.arguments?.getString("message") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            NotificationDetailScreen(
                titles = titles,
                message = message,
                time = time,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}