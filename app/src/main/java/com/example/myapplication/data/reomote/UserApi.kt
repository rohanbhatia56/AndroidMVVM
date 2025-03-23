package com.example.myapplication.data.reomote


import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): UserResponse
}