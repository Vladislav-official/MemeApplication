package com.example.memeapplication.ui.gallery

import androidx.lifecycle.*
import com.example.memeapplication.ApiService
import com.example.memeapplication.RetrofitBuild
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request


class GalleryViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }

    val text: LiveData<String> = _text

    fun getRandomAPI() = viewModelScope.launch {
        _text.value = RetrofitBuild.apiService.getRandomAPI().entries.get(0).Description
    }
}