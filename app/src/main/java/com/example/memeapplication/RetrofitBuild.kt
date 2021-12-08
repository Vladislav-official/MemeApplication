package com.example.memeapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuild {

    private const val BASE_URL = "https://api.publicapis.org/"

    private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}