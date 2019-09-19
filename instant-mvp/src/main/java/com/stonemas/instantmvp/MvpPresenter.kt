package com.stonemas.instantmvp

import android.util.Log
import androidx.lifecycle.*
import java.lang.ref.WeakReference

open class MvpPresenter<M : MvpModel, V : MvpView> : LifecycleObserver {

    protected val TAG = this::class.java.simpleName

    protected lateinit var model: M

    private lateinit var viewRef: WeakReference<V>

    private fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    private fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    internal fun attachView(view: V, lifecycle: Lifecycle) {
        viewRef = WeakReference(view)
        attachLifecycle(lifecycle)
    }

    internal fun detachView(lifecycle: Lifecycle) {
        detachLifecycle(lifecycle)
        viewRef.clear()
    }

    internal fun attachModel(model: M) {
        this.model = model
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        Log.d(TAG, "## onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {
        Log.d(TAG, "## onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected fun onPause() {
        Log.d(TAG, "## onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() {
        Log.d(TAG, "## onDestroy()")
    }

    protected val view get() = viewRef.get()

    protected val isViewAttached get() = ::viewRef.isInitialized && view != null

    protected val lifecycleOwner get() = view?.lifecycleOwner

    protected fun <T : Any?> observe(data: LiveData<T>?, observer: Observer<T>) {
        data?.observe(lifecycleOwner!!, observer)
    }
}
