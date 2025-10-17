package com.thechance.qurio.presentation.screen.buylife

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.thechance.qurio.databinding.LayoutBuyLifeBinding
import androidx.core.graphics.drawable.toDrawable
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BuyLifeDialogFragment : DialogFragment(), BuyLifeView {

    private var _binding: LayoutBuyLifeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter : BuyLifePresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        AndroidSupportInjection.inject(this)


        dialog.window?.apply {
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            decorView.setPadding(0, 0, 0, 0)
        }

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels - (32 * resources.displayMetrics.density)).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutBuyLifeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.loadLifeInfo()

        binding.buttonBuy.setOnClickListener {
            presenter.buyLife()
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonExit.setOnClickListener {
            dismiss()
        }
    }

    override fun showLifeCount(count: Int) {
        binding.textLifeCount.text = count.toString()
    }

    override fun showPrice(price: Int) {
        binding.textPrice.text = "$price X"
    }

    override fun showLoading() {
        // Optionally show a loading indicator
        binding.buttonBuy.isEnabled = false
    }

    override fun hideLoading() {
        // Optionally hide a loading indicator
        binding.buttonBuy.isEnabled = true
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onLifeBought(success: Boolean) {
        if (success) {
            Toast.makeText(requireContext(), "Life bought!", Toast.LENGTH_SHORT).show()
            dismiss()
        } else {
            showError("Failed to buy life")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }
}
