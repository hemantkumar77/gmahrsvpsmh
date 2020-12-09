package com.kumar.hemant.traintimetable2009.ui.check_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Check List"
    }
    val text: LiveData<String> = _text
}