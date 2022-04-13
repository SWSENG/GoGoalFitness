package com.example.gogoalfitness.statistic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryModel : ViewModel(){

    var weightStart = MutableLiveData<String>()
    var weightCurrent = MutableLiveData<String>()
    var weightChange = MutableLiveData<String>()
    var totalWorkout = MutableLiveData<String>()
    var totalMin = MutableLiveData<String>()
    var totalCalories = MutableLiveData<String>()
}