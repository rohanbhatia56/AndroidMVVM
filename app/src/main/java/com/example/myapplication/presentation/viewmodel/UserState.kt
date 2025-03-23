package com.example.myapplication.presentation.viewmodel

import com.example.myapplication.domain.model.User

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
