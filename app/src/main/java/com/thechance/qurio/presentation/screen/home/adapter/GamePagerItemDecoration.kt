package com.thechance.qurio.presentation.screen.home.adapter// CarouselItemDecoration.kt
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GamePagerItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = spacing
        outRect.right = spacing
        
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.left = (parent.width / 2) - (view.layoutParams.width / 2)
        }
        if (position == state.itemCount - 1) {
            outRect.right = (parent.width / 2) - (view.layoutParams.width / 2)
        }
    }
}