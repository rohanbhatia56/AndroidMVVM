package com.example.myapplication.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.UserListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "UserListScreen") {
                        composable("UserListScreen") {
                            UserListScreen(navController)
                        }
                        composable("UserScreen/{data}") { backStackEntry ->
                            val data = backStackEntry.arguments?.getString("data") ?: "No Data"
                            UserScreen(navController, data)
                        }
                    }
                }
            }
        }
    }
}