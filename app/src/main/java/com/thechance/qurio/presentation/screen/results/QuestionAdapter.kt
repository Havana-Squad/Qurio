package com.thechance.qurio.presentation.screen.results

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.thechance.qurio.R

class QuestionAdapter(
    val answers: List<String>,
    private val onAnswerClick: (Int) -> Unit
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    var selectedPosition: Int? = null
    var showCorrect: Boolean = false
    var correctAnswer: String? = null

    inner class ViewHolder(val view: LinearLayout) : RecyclerView.ViewHolder(view) {
        val answerText: TextView = view.findViewById(R.id.answer1)
        val answerLayout: LinearLayout = view.findViewById(R.id.answer1Layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.answerText.text = answers[position]

        holder.answerLayout.setBackgroundResource(
            if (showCorrect && selectedPosition == position) {
                if (answers[position] == correctAnswer) R.drawable.correct_answer_bg
                else R.drawable.incorrect_answer_bg
            } else if (!showCorrect && selectedPosition == position) {
                R.drawable.answer_clicked_bg
            } else {
                R.drawable.quetion_text_bg
            }
        )

        holder.view.setOnClickListener {
            if (!showCorrect) {
                selectedPosition = position
                notifyDataSetChanged()
                onAnswerClick(position)
            }
        }
    }

    override fun getItemCount(): Int = answers.size
}