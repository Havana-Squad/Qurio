package com.thechance.qurio.presentation.screen.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.thechance.qurio.R

class ExampleFragment : Fragment(), ExampleView {
    private val presenter: ExamplePresenter = ExamplePresenter.createInstance()
    private lateinit var messageTextView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        button = view.findViewById(R.id.btnGetData)
        messageTextView = view.findViewById(R.id.txtMessage)
        button.setOnClickListener {
            onButtonClick()
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onButtonClick() {
        presenter.getData()
    }

    override fun updateMessage(message: String) {
        messageTextView.text = message
    }
}