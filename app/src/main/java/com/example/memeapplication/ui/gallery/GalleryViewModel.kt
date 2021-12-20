package com.example.memeapplication.ui.gallery

import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkRequest
import com.example.memeapplication.ui.home.DaggerModule
import com.example.memeapplication.ui.work_with_api.*
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class GalleryViewModel : ViewModel() {

    private var allEntries = MutableLiveData<List<Entrie>>()

    val entries: LiveData<List<Entrie>> = allEntries
    private var retrofitBuild : RetrofitBuild? = null

    fun initRetrofit(retrofitBuild: RetrofitBuild) { this.retrofitBuild = retrofitBuild }

    val uploadWorkRequest: WorkRequest =
        OneTimeWorkRequestBuilder<NotifyWork>()
            .build()

    fun getRandomApi() = viewModelScope.launch {
        retrofitBuild!!.apisData.randomApi.collect { allApis ->
            allEntries.value = allApis
        }
    }
    fun getAllAPI() = viewModelScope.launch {
        retrofitBuild!!.apisData.allApis.collect{
            allApis -> allEntries.value = allApis
        }
    }

    //справкуа из общажития, справка с горисполкома в каком районе,
}