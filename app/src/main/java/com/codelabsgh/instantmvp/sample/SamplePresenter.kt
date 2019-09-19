package com.codelabsgh.instantmvp.sample

import androidx.lifecycle.Observer
import com.stonemas.instantmvp.MvpPresenter

class SamplePresenter : MvpPresenter<SampleModel, SampleView>() {

    override fun onCreate() {
        observe(model.toastMessageToShow, Observer<String> { sampleSnackMessage ->
            view?.showSampleSnack(sampleSnackMessage)
        })
    }

    fun requestSampleSnackMessage() {
        model.fetchSampleSnackMessage()
    }

}
