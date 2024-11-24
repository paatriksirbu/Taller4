package com.example.taller4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _items = MutableLiveData<MutableList<String>>(mutableListOf())
    val items: LiveData<MutableList<String>> get() = _items

    fun addItem(newItem: String) {
        _items.value?.add(newItem)
        _items.value = _items.value // Para notificar a los observadores
    }

    private val selectedItem = MutableLiveData<String>()

    fun selectItem(item: String) {
        selectedItem.value = item
    }

    fun getSelectedItem(): LiveData<String> {
        return selectedItem
    }
}
