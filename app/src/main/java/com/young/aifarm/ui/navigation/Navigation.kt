package com.young.aifarm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.young.aifarm.ui.screen.LoginScreen
import com.young.aifarm.ui.screen.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
        // 다른 화면들에 대한 composable 추가 예정.
    }
}