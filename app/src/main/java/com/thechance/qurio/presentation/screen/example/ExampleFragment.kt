package com.thechance.qurio.presentation.screen.example

import androidx.navigation.fragment.findNavController
import com.thechance.qurio.R
import com.thechance.qurio.R.*
import com.thechance.qurio.databinding.FragmentExampleBinding
import com.thechance.qurio.presentation.base.BaseFragment
import javax.inject.Inject

class ExampleFragment : BaseFragment<FragmentExampleBinding, ExampleView, ExamplePresenter>(),
    ExampleView {
    override val layoutIdFragment: Int
        get() = layout.fragment_example

    @Inject
    override lateinit var presenter: ExamplePresenter

    override fun setupViews() {
        binding.btnGetData.setOnClickListener {
            onButtonClick()
        }
    }

    override fun onButtonClick() {
        presenter.getData()
        findNavController().navigate(R.id.lastGamesFragment)
    }

    override fun updateMessage(message: String) {
        binding.txtMessage.text = message
    }
}