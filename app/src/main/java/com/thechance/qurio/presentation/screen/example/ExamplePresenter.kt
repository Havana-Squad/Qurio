package com.thechance.qurio.presentation.screen.example

import com.thechance.qurio.domain.model.LastGame
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import kotlinx.datetime.LocalDate
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class ExamplePresenter @Inject constructor(
    private val repository: ExampleRepository,
    private val gameRepository: GameRepository,
): BasePresenter<ExampleView>() {
    init {
        addLastGame()
    }

    fun addLastGame(){
        tryToExecute(
            callee = {
                gameRepository.addLastGame(
                    LastGame(
                        id = 0,
                        gameName = "Science and Natural",
                        coins = 300,
                        stars = 2,
                        duration = 12L.toDuration(DurationUnit.MINUTES),
                        date = LocalDate(year = 2025, monthNumber = 12, dayOfMonth = 12 )
                    )
                )
            },
            onSuccess = ::onAddLastGameSuccess,
            onError = ::onGetDataError,
            onStart = ::onGetDataStart,
        )
    }

    private fun onAddLastGameSuccess(d:Unit) {}
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