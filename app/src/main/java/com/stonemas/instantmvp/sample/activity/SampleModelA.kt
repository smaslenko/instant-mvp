package com.stonemas.instantmvp.sample.activity

import androidx.lifecycle.MutableLiveData
import com.stonemas.instantmvp.MvpModel

class SampleModelA : MvpModel() {

    val toastMessageToShow: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun fetchSampleSnackMessage() {
        // Assume that async network or DB request is being executed here, and the value returned
        val msgToShow = "Activity: Sample Snack Message!"
        toastMessageToShow.postValue(msgToShow)
    }

}
