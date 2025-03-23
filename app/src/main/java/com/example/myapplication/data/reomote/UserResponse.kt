package com.example.myapplication.data.reomote

import com.example.myapplication.data.reomote.modelDTO.UserDto
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("users")
    val users: List<UserDto>
)
