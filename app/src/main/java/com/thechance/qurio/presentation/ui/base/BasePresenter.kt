package com.thechance.qurio.presentation.ui.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BasePresenter<V: BaseView> {
    private var _view: V? = null
    protected val view: V
        get() = _view ?: throw IllegalStateException("View is not attached")
    private val job = SupervisorJob()
    protected val presenterScope = CoroutineScope(job + Dispatchers.Main)

    fun attachView(view: V) {
        this._view = view
        onViewAttached()
    }

    open fun onViewAttached() {}

    fun detachView() {
        job.cancel()
        _view = null
        onViewDetached()
    }

    open fun onViewDetached() {}

    protected fun <T> tryToExecute(
        callee: suspend () -> T,
        onSuccess: (suspend (T) -> Unit),
        onError: (suspend (Throwable) -> Unit),
        onStart: (suspend () -> Unit)? = null,
        onFinish: (suspend () -> Unit)? = null,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): Job {
        return presenterScope.launch(Dispatchers.Main) {
            try {
                onStart?.invoke()
                withContext(dispatcher) {
                    runCatching {
                        callee.invoke()
                    }
                }.onSuccess { result -> onSuccess.invoke(result) }
                .onFailure { throwable -> onError.invoke(throwable) }
            } finally {
                onFinish?.invoke()
            }
        }
    }
}