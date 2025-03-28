package com.example.myapplication.data.reomote


import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("users")
    suspend fun getUsers(): UserResponse

    @POST("userData")
    suspend fun sendUserData(text: String)
}