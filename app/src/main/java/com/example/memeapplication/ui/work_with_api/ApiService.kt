package com.example.memeapplication.ui.work_with_api

import retrofit2.http.GET


interface ApiService {
    @GET("random")
    suspend fun getRandomAPI(): MyResponse

    @GET("entries")
    suspend fun getAllAPI(): MyResponse
}