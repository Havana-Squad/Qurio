package com.thechance.qurio.presentation.screen.last_games_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.LastGameBinding
import com.thechance.qurio.domain.model.LastGame

class LastGameAdapter(val lastGames: List<LastGame>) :
    RecyclerView.Adapter<LastGameAdapter.LastGameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.last_game,
            parent,
            false
        )
        return LastGameViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: LastGameViewHolder,
        position: Int
    ) {
        val currentGame = lastGames[position]
        holder.binding.apply {
            questionDateText.text = currentGame.date.toString()
            coin.text = currentGame.coins.toString()
            star.text = currentGame.stars.toString()
            time.text = currentGame.duration.toString()
            genre.text = currentGame.gameName
        }
    }

    override fun getItemCount() = lastGames.size

    class LastGameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = LastGameBinding.bind(viewItem)
    }
}