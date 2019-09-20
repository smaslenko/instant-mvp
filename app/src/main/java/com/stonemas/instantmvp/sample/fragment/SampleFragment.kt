package com.stonemas.instantmvp.sample.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.stonemas.instantmvp.MvpFragment
import com.stonemas.instantmvp.sample.R
import com.stonemas.instantmvp.sample.activity.SampleModelF
import com.stonemas.instantmvp.sample.activity.SamplePresenterF
import com.stonemas.instantmvp.sample.activity.SampleViewF
import kotlinx.android.synthetic.main.fragment_sample.*

/**
 * A simple [Fragment] subclass.
 */
class SampleFragment : MvpFragment<SampleModelF, SampleViewF, SamplePresenterF>(), SampleViewF {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun showSampleSnack(message: String) {
        Snackbar.make(sampleTv, message, Snackbar.LENGTH_LONG).show()
    }

    override val expandedOnStart = false
}
