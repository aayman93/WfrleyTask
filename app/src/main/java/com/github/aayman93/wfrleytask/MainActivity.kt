package com.github.aayman93.wfrleytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.github.aayman93.wfrleytask.navigation.WfrleyApp
import com.github.aayman93.wfrleytask.ui.theme.WfrleyTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WfrleyTaskTheme {
                val navController = rememberNavController()
                WfrleyApp(
                    navController = navController
                )
            }
        }
    }
}