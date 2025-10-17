package com.thechance.qurio.presentation.screen.results.custom


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.thechance.qurio.R
import com.thechance.qurio.databinding.ResultComponentBinding

class ResultCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ResultComponentBinding.inflate(LayoutInflater.from(context), this)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ResultCardView, 0, 0).apply {
            try {
                val resultText = getString(R.styleable.ResultCardView_resultText)
                val blurVisible = getBoolean(R.styleable.ResultCardView_blurVisible, true)
                val starsVisible = getBoolean(R.styleable.ResultCardView_starsVisible, true)
                setResultText(resultText ?: "")
                showBlur(blurVisible)
                showStars(starsVisible)
            } finally {
                recycle()
            }
        }
    }

    fun updateResult(correctAnswers: Int, totalQuestions: Int) {
        if (totalQuestions == 0) return

        val ratio = correctAnswers.toFloat() / totalQuestions
        val stars = when {
            ratio >= 1f -> 3
            ratio >= 0.66f -> 2
            ratio >= 0.33f -> 1
            else -> 0
        }


        if (stars >= 1) {
            setResultText("Great job")
        } else {
            setResultText("You lose")
        }

        val starViews = listOf(binding.star1, binding.star2, binding.star3)
        starViews.forEachIndexed { index, star ->
            star.isVisible = index < stars
        }
        if (stars > 0) {
            animateStars()
            binding.txtResult.setOutlineColor(Color.parseColor("#1980B2"))
            binding.blurBackground.setImageResource(R.drawable.ic_success_shadow)
            binding.ribbon.setImageResource(R.drawable.ic_success_ribbon)
        } else {
            binding.txtResult.setOutlineColor(Color.parseColor("#E6311F"))
            binding.blurBackground.setImageResource(R.drawable.ic_failed_shadow)
            binding.ribbon.setImageResource(R.drawable.ic_failed_ribbon)
        }
    }

    fun setResultText(text: String) {
        binding.txtResult.text = text
    }

    fun showBlur(show: Boolean) {
        binding.blurBackground.isVisible = show
    }

    fun setBlurBackground(resId: Int) {
        binding.blurBackground.setImageResource(resId)
    }

    fun animateStars() {
        val stars = listOf(binding.star1, binding.star2, binding.star3)

        stars.forEachIndexed { index, star ->
            star.scaleX = 0f
            star.scaleY = 0f
            star.alpha = 0f

            star.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setStartDelay((index * 200).toLong())
                .setDuration(400)
                .start()
        }
    }

    fun showStars(show: Boolean) {
        binding.starsContainer.isVisible = show
    }

    fun setReward(value: String) {
        binding.coinsReward.text = value
    }

    fun setCorrectCount(value: String) {
        binding.correctAnswersCount.text = value
    }

    fun setIncorrectCount(value: String) {
        binding.inCorrectAnswersCount.text = value
    }

    fun setSkippedCount(value: String) {
        binding.skippedAnswersCount.text = value
    }
}