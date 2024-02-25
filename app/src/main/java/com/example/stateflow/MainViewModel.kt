package com.example.stateflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {

    /** LiveData 형태 **/
    private var mutableLiveData = MutableLiveData<Int>()
    val liveData : LiveData<Int>
        get() = mutableLiveData


    /** StateFlow 형태 **/
    val mutableStateFlow = MutableStateFlow(0)

    val transformationStateFlow = mutableStateFlow.asStateFlow().map { it * it }

    fun setData(value : Int)
    {
        mutableStateFlow.value = value
    }
}