package com.thechance.qurio.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.GameItemBinding
import com.thechance.qurio.presentation.screen.games_screen.GameItem
import com.thechance.qurio.presentation.screen.home.HomeView

class GamesPagerAdapter(private val listener: HomeView) : RecyclerView.Adapter<GamesPagerAdapter.ViewHolder>() {
        private val items: List<GameItem> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = GameItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            gameTitle.text = items[position].title
            gameImg.setImageResource(items[position].drawables.img)
            gradientBackground.setBackgroundResource(items[position].drawables.shadow)
        }
        holder.binding.root.setOnClickListener {
            listener.navigateToGame(items[position].id)
        }
    }

    fun setItems(newItems: List<GameItem>) {
        (items as MutableList).clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}