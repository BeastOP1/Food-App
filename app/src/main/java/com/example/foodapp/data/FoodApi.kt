package com.example.foodapp.data

import retrofit2.http.GET

interface FoodApi {

    @GET("/foods")
    suspend fun getFood(): List<String>
}