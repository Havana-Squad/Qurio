package com.thechance.qurio.presentation.screen.results.custom
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.thechance.qurio.R

import com.thechance.qurio.databinding.QuestionTextBinding

class QuestionTextCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: QuestionTextBinding =
        QuestionTextBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.QuestionTextCard)

            val question = typedArray.getString(R.styleable.QuestionTextCard_questionText) ?: ""
            val number = typedArray.getString(R.styleable.QuestionTextCard_questionNumber) ?: ""

            typedArray.recycle()

            binding.questionText.text = question
            binding.questionNumberText.text = number
        }
    }

    fun setQuestion(question: String) {
        binding.questionText.text = question
    }

    fun setQuestionNumber(numberText: String) {
        binding.questionNumberText.text = numberText
    }
}