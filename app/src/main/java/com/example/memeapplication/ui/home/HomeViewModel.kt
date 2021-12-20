package com.example.memeapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Home fragment"
    }
    val text: LiveData<String> = _text
}