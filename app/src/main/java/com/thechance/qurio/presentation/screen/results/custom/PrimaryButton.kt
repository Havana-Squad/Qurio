package com.thechance.qurio.presentation.screen.results.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.thechance.qurio.R
import com.thechance.qurio.databinding.PrimaryButtonBinding

class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: PrimaryButtonBinding =
        PrimaryButtonBinding.inflate(LayoutInflater.from(context), this)

    private var isCustomEnabled = true
    private val gradientPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var onPrimaryColor = ContextCompat.getColor(context, R.color.on_primary)
    val startColor = ColorUtils.setAlphaComponent(onPrimaryColor, 130)
    init {
        orientation = VERTICAL
        setWillNotDraw(false)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PrimaryButton)
            try {
                isCustomEnabled =
                    typedArray.getBoolean(R.styleable.PrimaryButton_primaryButtonEnabled, true)

                val textAttr =
                    context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.text))
                val textResId = textAttr.getResourceId(0, 0)
                val textValue = if (textResId != 0) {
                    context.getString(textResId)
                } else {
                    textAttr.getString(0) ?: context.getString(R.string.submit)
                }
                textAttr.recycle()

                binding.textViewLabel.text = textValue
                setButtonEnabled(isCustomEnabled)
            } finally {
                typedArray.recycle()
            }
        }
    }


    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        if (!isCustomEnabled) return

        val gradientHeight = height * 0.30f
        val radius = 16f * resources.displayMetrics.density

        val gradient = LinearGradient(
            0f,
            height - gradientHeight,
            0f,
            height.toFloat(),
            Color.TRANSPARENT,
            startColor,
            Shader.TileMode.CLAMP
        )
        gradientPaint.shader = gradient

        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val path = Path()
        path.addRoundRect(rect, radius, radius, Path.Direction.CW)

        canvas.save()
        canvas.clipPath(path)
        canvas.drawRect(
            0f,
            height - gradientHeight,
            width.toFloat(),
            height.toFloat(),
            gradientPaint
        )

        canvas.restore()
    }

    fun setButtonEnabled(enabled: Boolean) {
        isCustomEnabled = enabled
        binding.rootLayout.isEnabled = enabled
        binding.textViewLabel.isEnabled = enabled
        binding.rootLayout.background =
            ContextCompat.getDrawable(context, R.drawable.primary_button_shape)
        invalidate()
    }

    fun setText(text: String) {
        binding.textViewLabel.text = text
    }

    fun getText(): String = binding.textViewLabel.text.toString()
}