package com.app.abasiccommondiary.ui.rto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RtoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is RTO Fragment"
    }
    val text: LiveData<String> = _text
}