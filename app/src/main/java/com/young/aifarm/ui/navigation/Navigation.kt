package com.young.aifarm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.young.aifarm.ui.screen.AnalyzeScreen
import com.young.aifarm.ui.screen.CheckListScreen
import com.young.aifarm.ui.screen.LoginScreen
import com.young.aifarm.ui.screen.WeatherScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.young.aifarm.R
import com.young.aifarm.ui.screen.MainScreen
import com.young.aifarm.util.ANALYZE
import com.young.aifarm.util.CHECKLIST
import com.young.aifarm.util.WEATHER


@Composable
fun Navigation(navController: NavHostController, isLoggedIn: Boolean) {
    NavHost(navController = navController, startDestination = if (isLoggedIn) "main" else "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
        composable("weather") {
            WeatherScreen()
        }
        composable("analyze") {
            AnalyzeScreen()
        }
        composable("checklist") {
            CheckListScreen()
        }
    }
}

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val route: String
) {
    object Weather : BottomNavItem(R.string.bn_text_weather, R.drawable.ic_weather, WEATHER)
    object Analyze : BottomNavItem(R.string.bn_text_analyze, R.drawable.ic_analyze, ANALYZE)
    object CheckList :
        BottomNavItem(R.string.bn_text_check_list, R.drawable.ic_checklist, CHECKLIST)
}