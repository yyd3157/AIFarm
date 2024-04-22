package com.young.aifarm.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kakao.sdk.user.UserApiClient
import com.young.aifarm.ui.navigation.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationView(navController = navController)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = BottomNavItem.Weather.route) {
            composable(BottomNavItem.Weather.route) {
                WeatherScreen()
            }
            composable(BottomNavItem.Analyze.route) {
                AnalyzeScreen()
            }
            composable(BottomNavItem.CheckList.route) {
                CheckListScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationView(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Weather,
        BottomNavItem.Analyze,
        BottomNavItem.CheckList
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                label = {
                    androidx.compose.material.Text(
                        stringResource(id = item.title),
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun logoutKakao() {
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e("logouts", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        } else {
            Log.i("logoute", "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
}