package com.young.aifarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.young.aifarm.ui.navigation.Navigation
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
    AIFarmTheme {
        Navigation(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        MyApp()
}