package com.young.aifarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.young.aifarm.ui.navigation.Navigation
import com.young.aifarm.ui.screen.BottomNavigationView
import com.young.aifarm.ui.theme.AIFarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val isLoggedIn = remember { mutableStateOf(false) } // 로그인 상태를 관리합니다.

    AIFarmTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Navigation(navController = navController, isLoggedIn = isLoggedIn.value)
            if (isLoggedIn.value) {
                BottomNavigationView(navController = navController)
            }
        }
    }
}
