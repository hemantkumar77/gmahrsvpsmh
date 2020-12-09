package com.kumar.hemant.traintimetable2009.ui.rto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RtoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "RTO Numbers in India"
    }
    val text: LiveData<String> = _text
}