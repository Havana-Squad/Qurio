package com.thechance.qurio.presentation.screen.example

import com.thechance.qurio.presentation.base.BaseView

interface ExampleView: BaseView {
    fun onButtonClick()
    fun updateMessage(message: String)
}