package com.example.memeapplication.ui.work_with_api

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApisRemoteDataSource(
    private var apisService: ApiService
) {
    val randomApi: Flow<List<Entrie>> = flow {
            val apInfo = apisService.getRandomAPI()
            emit(apInfo.entries)
    }
    val allApis: Flow<List<Entrie>> = flow {
            val apInfo = apisService.getAllAPI()
            emit(apInfo.entries)
    }
}


class RetrofitBuild @Inject constructor(retrofit: Retrofit){

//    val BASE_URL : String
//
//    private var client : OkHttpClient = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
//
//    private fun getRetrofit() = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)
    val apisData: ApisRemoteDataSource = ApisRemoteDataSource(apiService)


}