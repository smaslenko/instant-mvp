package com.stonemas.instantmvp.sample.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.stonemas.instantmvp.MvpActivity
import com.stonemas.instantmvp.sample.R
import com.stonemas.instantmvp.sample.fragment.SampleFragment
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.content_main.*

class SampleActivity : MvpActivity<SampleModelA, SampleViewA, SamplePresenterA>(), SampleViewA {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            presenter.requestSampleSnackMessage()
        }

        button.setOnClickListener { showSampleFragment() }
    }


    override fun showSampleSnack(message: String) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSampleFragment() {
        SampleFragment().show(supportFragmentManager, null)
    }

}
