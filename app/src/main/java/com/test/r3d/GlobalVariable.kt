package com.test.r3d

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
object GlobalVariable  {
    private val _myVariable = MutableLiveData<String>().apply { value = "" }
    val myVariable: LiveData<String> get() = _myVariable

    fun updateVariable(value: String) {
        _myVariable.value = value
    }

}