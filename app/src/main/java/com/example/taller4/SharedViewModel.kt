package com.example.taller4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val selectedItem = MutableLiveData<String>()

    fun selectItem(item: String) {
        selectedItem.value = item
    }

    fun getSelectedItem(): LiveData<String> {
        return selectedItem
    }
}