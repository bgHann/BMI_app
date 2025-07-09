package com.example.bmi_app_nhom17

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bmi_app_nhom17.data.model.NotificationItem
import com.example.bmi_app_nhom17.ui.screen.BmiCalculatorScreen
import com.example.bmi_app_nhom17.ui.screen.BmiResultScreen
import com.example.bmi_app_nhom17.ui.screen.Dashboard
import com.example.bmi_app_nhom17.ui.screen.DetailsScreen
import com.example.bmi_app_nhom17.ui.screen.EnterOtpScreen
import com.example.bmi_app_nhom17.ui.screen.NotificationDetailScreen
import com.example.bmi_app_nhom17.ui.screen.NotificationScreen
import com.example.bmi_app_nhom17.ui.screen.Porgot_pass
import com.example.bmi_app_nhom17.ui.screen.ResultsScreen
import com.example.bmi_app_nhom17.ui.screen.SignUpScreen
import com.example.bmi_app_nhom17.ui.screen.TrackScreen
import com.example.bmi_app_nhom17.ui.screen.WelcomeScreen
import com.example.bmi_app_nhom17.ui.screen.frame1
import com.example.bmi_app_nhom17.ui.screen.profileSreen
import com.example.bmi_app_nhom17.ui.screen.settingScreen
import com.example.bmi_app_nhom17.ui.screen.signIn
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel
import com.example.bmi_app_nhom17.viewmodel.NotificationViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val bmiViewModel: BmiViewModel = viewModel()
    val NotificationViewModel: NotificationViewModel = viewModel()

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
                onBMI = { bmi ->
                    navController.navigate("Bmi_Result/${bmi}")
                }
            )
        }

        composable(route = "Bmi_Result/{bmi}",
                arguments = listOf(navArgument("bmi") { type = NavType.FloatType })
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            BmiResultScreen(
                bmi = bmi,
                onDetails = {bmi -> navController.navigate("Details/${bmi}")}
            )
        }

        composable("Setting") {
            settingScreen(
                onCenter = { navController.navigate("Dashboard") },
                onPrileclick = { navController.navigate("Profile") },
                onSignOut = { navController.navigate("signIn") },
                onNotifications = { navController.navigate("Notification") },
                onleft = { navController.navigate("Tracks") }
            )
        }

        composable(
            route = "Tracks/{bmi}/{category}/{title}",
            arguments = listOf(
                navArgument("bmi") { type = NavType.FloatType },
                navArgument("category") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            val title = backStackEntry.arguments?.getString("title") ?: "title"

            TrackScreen(
                bmi = bmi,
                category = category,
                titles = title,
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
                    navController.navigate("Results/${bmiValue}/${category}")
                }
            )
        }

        composable(
            route = "Results/{bmi}/{category}",
            arguments = listOf(
                navArgument("bmi") { type = NavType.FloatType },
                navArgument("category") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            ResultsScreen(
                bmi = bmi,
                category = category,
                onHome = {
                    navController.navigate("Dashboard")
                }
            )
        }


        composable("Notification") {
            val notificationViewModel: NotificationViewModel = viewModel()

            notificationViewModel.notifications = listOf(
                NotificationItem(1, "Thông báo 1", "Bạn có tin nhắn mới", "10:00 AM"),
                NotificationItem(2, "Thông báo 2", "Cập nhật hệ thống", "11:30 AM"),
                NotificationItem(3, "Thông báo 3", "Sự kiện sắp diễn ra", "12:45 PM")
            )

            NotificationScreen(
                viewModel = notificationViewModel,
                onBack = { navController.popBackStack() },
                onClickItem = { message ->
                    val selected = notificationViewModel.notifications.find { it.message == message }
                    selected?.let {
                        navController.navigate("NotificationDetail/${it.titles}/${it.message}/${it.time}")
                    }
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