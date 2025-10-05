package com.thechance.qurio.presentation.ui.example

import com.thechance.qurio.presentation.ui.base.BaseView

interface ExampleView: BaseView {
    fun onButtonClick()
    fun updateMessage(message: String)
}