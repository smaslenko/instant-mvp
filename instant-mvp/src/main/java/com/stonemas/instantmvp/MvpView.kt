package com.stonemas.instantmvp

import androidx.lifecycle.LifecycleOwner

interface MvpView {
    val lifecycleOwner get() = this as LifecycleOwner
}
