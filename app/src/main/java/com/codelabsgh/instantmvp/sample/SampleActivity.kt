package com.codelabsgh.instantmvp.sample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.stonemas.instantmvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class SampleActivity : MvpActivity<SampleModel, SampleView, SamplePresenter>(), SampleView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            presenter.requestSampleSnackMessage()
        }
    }


    override fun showSampleSnack(message: String) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show()
    }

}
