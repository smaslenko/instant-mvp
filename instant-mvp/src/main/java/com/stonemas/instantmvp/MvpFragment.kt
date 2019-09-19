package com.stonemas.instantmvp

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class MvpFragment<M : MvpModel, V : MvpView, P : MvpPresenter<M, V>> : BottomSheetDialogFragment() {

    protected val model: M by lazy {
        ViewModelProviders.of(this).get(getMClass())
    }

    protected val presenter: P by lazy {
        @Suppress("RemoveExplicitTypeArguments")
        (initPresenter<M, V, P>(this, model))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V, lifecycle)
    }

    override fun onDetach() {
        super.onDetach()

        presenter.detachView(lifecycle)
    }
}
