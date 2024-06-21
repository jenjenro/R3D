package com.test.r3d

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class BluetoothViewModel @Inject constructor() : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    fun setData(newData: String) {
        _data.value = newData
        Log.i("BluetoothViewModel", newData)
    }

    /*fun setData(newData: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _data.value = newData
                Log.i("BluetoothViewModel", newData)
            }
        }
    }
    */
}