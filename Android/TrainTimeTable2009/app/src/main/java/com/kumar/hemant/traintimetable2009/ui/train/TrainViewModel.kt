package com.kumar.hemant.traintimetable2009.ui.train

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Timer"
    }
    val text: LiveData<String> = _text
}