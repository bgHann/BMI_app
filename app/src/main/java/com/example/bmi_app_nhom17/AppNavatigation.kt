package com.example.bmi_app_nhom17

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bmi_app_nhom17.data.model.NotificationItem
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
                onSendOtpClick = { navController.navigate("enterOtp") }
            )
        }

        composable("enterOtp") {
            EnterOtpScreen(onResetClick = { navController.navigate("signIn") })
        }

        composable("Dashboard") { backStackEntry ->
            val bmi = backStackEntry.arguments?.getFloat("bmi") ?: 0f
            val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
            val title = backStackEntry.arguments?.getString("title") ?: "title"

            Dashboard(
                onBMI = { navController.navigate("BMI") },
                onRight = { navController.navigate("Setting") },
                onleft = { navController.navigate("Tracks/$bmi/$category/$title") }
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
            val category = bmiViewModel.getCategory(bmi) // lấy từ ViewModel

            BmiResultScreen(
                bmi = bmi,
                category = category,             // ✅ thêm dòng này
                onDetails = { bmiValue -> navController.navigate("Details/$bmiValue") },
                onHome = { navController.navigate("Dashboard") },
                viewModel = bmiViewModel         // ✅ thêm dòng này
            )

        }


        composable("Setting") {
            settingScreen(
                onCenter = { navController.navigate("Dashboard") },
                onPrileclick = { navController.navigate("Profile") },
                onSignOut = { navController.navigate("signIn") },
                onNotifications = { navController.navigate("Notification") },
                onleft = { navController.navigate("Tracks/0.0/Unknown/title") } // mặc định nếu chưa có
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
                title = title,
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
                onResult = { bmiValue, category ->
                    navController.navigate("Results/$bmiValue/$category")
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
            val category = bmiViewModel.getCategory(bmi) // hoặc lấy từ Result trước đó nếu có

            BmiResultScreen(
                bmi = bmi,
                category = category, // ✅ truyền vào
                onDetails = { bmiValue -> navController.navigate("Details/$bmiValue") },
                onHome = { navController.navigate("Dashboard") },
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
