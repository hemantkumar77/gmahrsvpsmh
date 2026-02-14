package com.app.abasiccommondiary.ui.pot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PotViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Pot Fragment"
    }
    val text: LiveData<String> = _text
}