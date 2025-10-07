package com.thechance.qurio.presentation.screen.example

import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.presentation.base.BasePresenter

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

    /*
    * temporary builder. will be replaced with di in actual features*/
    companion object {
        fun createInstance(): ExamplePresenter {
            return ExamplePresenter(ExampleRepositoryImpl())
        }
    }
}