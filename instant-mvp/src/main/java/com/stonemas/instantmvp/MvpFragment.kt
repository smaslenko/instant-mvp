package com.stonemas.instantmvp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
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

        if (expandedOnStart) expandFragment(true)
    }

    override fun onDetach() {
        super.onDetach()

        presenter.detachView(lifecycle)
    }

    protected open val expandedOnStart = true

    @Suppress("SameParameterValue")
    protected fun expandFragment(expand: Boolean) {
        val bottomSheet = dialog?.findViewById<View>(R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height =
                if (expand) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT

        val behavior = BottomSheetBehavior.from(bottomSheet)

        if (expand) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}
