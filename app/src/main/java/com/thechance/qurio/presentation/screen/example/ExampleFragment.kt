package com.thechance.qurio.presentation.screen.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentExampleBinding
import com.thechance.qurio.presentation.base.BaseFragment
import javax.inject.Inject

class ExampleFragment : BaseFragment<FragmentExampleBinding, ExampleView, ExamplePresenter>(), ExampleView {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_example

    @Inject
    override lateinit var presenter: ExamplePresenter

    override fun setupViews() {
        binding.btnGetData.setOnClickListener {
            onButtonClick()
        }
    }

    override fun onButtonClick() {
        presenter.getData()
    }

    override fun updateMessage(message: String) {
        binding.txtMessage.text = message
    }
}