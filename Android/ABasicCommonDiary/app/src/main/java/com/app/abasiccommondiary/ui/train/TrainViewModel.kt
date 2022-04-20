package com.app.abasiccommondiary.ui.train

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is Train Fragment"
    }
    val text: LiveData<String> = _text
}