package com.stonemas.instantmvp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders


@SuppressLint("Registered")
open class MvpActivity<M : MvpModel, V : MvpView, P : MvpPresenter<M, V>> : AppCompatActivity() {

    val model: M by lazy {
        ViewModelProviders.of(this).get(getMClass())
    }

    protected val presenter: P by lazy {
        @Suppress("RemoveExplicitTypeArguments")
        (initPresenter<M, V, P>(this, model))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V, lifecycle)
    }

    override fun onDestroy() {
        presenter.detachView(lifecycle)
        super.onDestroy()
    }
}
