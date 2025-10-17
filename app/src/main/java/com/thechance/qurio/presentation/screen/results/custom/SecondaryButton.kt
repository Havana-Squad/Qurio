package com.thechance.qurio.presentation.screen.results.custom
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.thechance.qurio.R
import com.thechance.qurio.databinding.SecondaryButtonBinding


class SecondaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: SecondaryButtonBinding =
        SecondaryButtonBinding.inflate(LayoutInflater.from(context), this)
    private var isCustomEnabled = true

    init {
        orientation = VERTICAL

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SecondaryButton)
            try {
                isCustomEnabled = typedArray.getBoolean(
                    R.styleable.SecondaryButton_secondaryButtonEnabled,
                    true
                )

                val textAttr =
                    context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.text))
                val textResId = textAttr.getResourceId(0, 0)
                val textValue = if (textResId != 0) {
                    context.getString(textResId)
                } else {
                    textAttr.getString(0) ?: context.getString(R.string.submit)
                }
                textAttr.recycle()

                setEnabledState(isCustomEnabled)
                binding.textViewLabel.text = textValue
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun setEnabledState(enabled: Boolean) {
        binding.rootLayout.isEnabled = enabled
        binding.textViewLabel.isEnabled = enabled
        binding.rootLayout.background =
            ContextCompat.getDrawable(context, R.drawable.secondary_button_shape)
        binding.textViewLabel.setTextColor(
            ContextCompat.getColorStateList(context, R.color.primary)
        )
    }

    fun setButtonEnabled(enabled: Boolean) {
        isCustomEnabled = enabled
        setEnabledState(enabled)
    }

    fun setText(text: String) {
        binding.textViewLabel.text = text
    }

    fun getText(): String = binding.textViewLabel.text.toString()
}