package com.thechance.qurio.presentation.ui.example

import com.thechance.qurio.presentation.domain.repository.ExampleRepository
import com.thechance.qurio.presentation.ui.base.BasePresenter

class ExamplePresenter(
    private val repository: ExampleRepository
): BasePresenter<ExampleView>() {

    fun getData() {
        tryToExecute(
            callee = repository::getData,
            onStart = ::onGetDataStart,
            onSuccess = ::onGetDataSuccess,
            onError = ::onGetDataError
        )
    }

    private fun onGetDataStart(){
        view.updateMessage("Loading...")
    }

    private fun onGetDataSuccess(data: String) {
        view.updateMessage(data)
    }

    private fun onGetDataError(throwable: Throwable) {
        view.updateMessage("Error: ${throwable.message}")
    }
}