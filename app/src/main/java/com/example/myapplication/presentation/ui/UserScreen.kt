package com.example.myapplication.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UserScreen(navController: NavController, data: String){
    Column {
        Text(text = "Received: $data")
        Button(onClick = { navController.navigate("UserListScreen") }) {
            Text("Go to Screen B")
        }
    }
}