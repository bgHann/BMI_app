package com.example.bmi_app_nhom17

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bmi_app_nhom17.model.NotificationItem
import com.example.bmi_app_nhom17.ui.screen.*
import com.example.bmi_app_nhom17.viewmodel.NotificationViewModel
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val bmiViewModel: BmiViewModel = viewModel()
    val notificationViewModel: NotificationViewModel = viewModel() // ✅ sửa tên biến

    NavHost(navController = navController, startDestination = "frame1") {
        composable("frame1") {
            frame1(onNextClick = { navController.navigate("welcome") })
        }

        composable("welcome") {
            WelcomeScreen(onStartClick = { navController.navigate("signIn") })
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
                onOtpSent = { email -> navController.navigate("enterOtp/$email") }
            )
        }

        composable("enterOtp/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            EnterOtpScreen(
                email = email,
                onResetClick = { navController.navigate("comfirmPass/$email") })
        }

        composable("comfirmPass/{email}") {backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ComfirmPassworkScreen(
                email = email,
                onComfirmClick = {navController.navigate("signIn")})
        }

        composable("Dashboard") { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = bmiViewModel.getCategory(bmi)
            val comment = bmiViewModel.getComment(bmi)

            Dashboard(
                onBMI = { navController.navigate("BMI") },
                onRight = { navController.navigate("Setting") },
                onleft = { navController.navigate("Tracks/$bmi/$category/$comment") }
            )
        }

        composable("BMI") {
            BmiCalculatorScreen(
                viewModel = bmiViewModel, // ✅ thêm dòng này
                onBMI = { bmi ->
                    navController.navigate("Bmi_Result/$bmi")
                }
            )
        }

        composable(
            route = "Bmi_Result/{bmi}",
            arguments = listOf(navArgument("bmi") { type = NavType.FloatType })
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = bmiViewModel.getCategory(bmi)
            BmiResultScreen(
                bmi = bmi,
                category = category,             // ✅ thêm dòng này
                onDetails = { bmi -> navController.navigate("Details/$bmi") },
            )

        }

        composable("Setting") {
            settingScreen(
                onCenter = { navController.navigate("Dashboard") },
                onPrileclick = { navController.navigate("Profile") },
                onSignOut = { navController.navigate("signIn") },
                onNotifications = { navController.navigate("Notification") },
                onleft = { navController.navigate("Tracks/0.0/Unknown/comment") } // mặc định nếu chưa có
            )
        }

        composable(
            route = "Tracks/{bmi}/{category}/{comment}",
            arguments = listOf(
                navArgument("bmi") { type = NavType.FloatType },
                navArgument("category") { type = NavType.StringType },
                navArgument("comment") { type = NavType.StringType }
            )
        ) {
            TrackScreen(
                onleft = { navController.navigate("Dashboard") },
                onCenter = { navController.navigate("Dashboard") },
                onRight = { navController.navigate("Setting") },
                viewModel = bmiViewModel // ✅ thêm dòng này
            )

        }

        composable("Profile") {
            profileSreen(onBack = { navController.navigate("Setting") })
        }

        composable(
            route = "Details/{bmi}",
            arguments = listOf(navArgument("bmi") { type = NavType.FloatType })
        ) { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            DetailsScreen(
                bmi = bmi,
                onResult = { bmi, category ->
                    navController.navigate("Results/$bmi/$category")
                },
                viewModel = bmiViewModel // ✅ thêm nếu cần
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
            val category = bmiViewModel.getCategory(bmi)
            ResultsScreen(
                bmi = bmi,
                category = category,
                onHome = {navController.navigate("Dashboard")},
                viewModel = bmiViewModel
            )
        }

        composable("Notification") {
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
            "NotificationDetail/{title}/{message}/{time}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("message") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val message = backStackEntry.arguments?.getString("message") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            NotificationDetailScreen(
                titles = title,
                message = message,
                time = time,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}