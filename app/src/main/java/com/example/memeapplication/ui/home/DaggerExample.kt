package com.example.memeapplication.ui.home

import com.example.memeapplication.ui.work_with_api.ApiService
import com.example.memeapplication.ui.work_with_api.ApisRemoteDataSource
import com.example.memeapplication.ui.work_with_api.RetrofitBuild
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaggerModule {

    @Provides
    fun BASE_URL() = "https://api.publicapis.org/"

    @Provides
    fun client() : OkHttpClient = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()

    @Provides
    @Singleton
    fun getRetrofit(BASE_URL : String, client : OkHttpClient) = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
           .addConverterFactory(GsonConverterFactory.create())
            .build()


}