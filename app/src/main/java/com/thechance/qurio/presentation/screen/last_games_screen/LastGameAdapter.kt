package com.thechance.qurio.presentation.screen.last_games_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.LastGameBinding

class LastGameAdapter(val lastGames: List<LastGameUi>) :
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
            questionDateText.text = currentGame.date
            coin.text = currentGame.coins
            star.text = currentGame.stars
            time.text = currentGame.duration
            genre.text = currentGame.gameName
        }
    }

    override fun getItemCount() = lastGames.size

    class LastGameViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = LastGameBinding.bind(viewItem)
    }
}