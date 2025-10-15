package com.thechance.qurio.presentation.screen.games_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.GameItemBinding

class GamesAdapter(
    val games: List<GameItem>,
    val listener: GamesView
    ): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.game_item,
            parent,
            false
        )
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            gameTitle.text = games[position].title
            gameImg.setImageResource(games[position].drawables.img)
            gradientBackground.setBackgroundResource(games[position].drawables.shadow)
        }
        holder.binding.root.setOnClickListener {
            listener.onGameItemClick(games[position].id)
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class GameViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val binding = GameItemBinding.bind(viewItem)
    }
}