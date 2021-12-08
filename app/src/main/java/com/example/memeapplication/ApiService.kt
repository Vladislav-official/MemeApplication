package com.example.memeapplication

import retrofit2.http.GET


interface ApiService {
    @GET("random")
    suspend fun getRandomAPI(): MyResponse

}