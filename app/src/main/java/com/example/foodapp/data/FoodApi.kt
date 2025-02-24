package com.example.foodapp.data

import com.example.foodapp.data.models.AuthResponse
import com.example.foodapp.data.models.LogInRequest
import com.example.foodapp.data.models.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {

    @GET("/foods")
    suspend fun getFood(): List<String>

    @POST("/auth/signup")
    suspend fun signUp(
        @Body request : SignUpRequest
    ): AuthResponse

    @POST("/auth/login")
    suspend fun login(
        @Body request: LogInRequest
    ): AuthResponse


}