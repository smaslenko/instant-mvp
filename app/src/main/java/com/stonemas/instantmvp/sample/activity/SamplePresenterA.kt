package com.stonemas.instantmvp.sample.activity

import androidx.lifecycle.Observer
import com.stonemas.instantmvp.MvpPresenter

class SamplePresenterA : MvpPresenter<SampleModelA, SampleViewA>() {

    override fun onCreate() {
        super.onCreate() // Not required

        observe(model.toastMessageToShow, Observer<String> { sampleSnackMessage ->
            view?.showSampleSnack(sampleSnackMessage)
        })
    }

    fun requestSampleSnackMessage() {
        model.fetchSampleSnackMessage()
    }

}
