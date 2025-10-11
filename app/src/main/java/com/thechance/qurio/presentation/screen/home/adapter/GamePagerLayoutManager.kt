package com.thechance.qurio.presentation.screen.home.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class GamePagerLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChildren()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleChildren()
        return scrolled
    }

    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = 0.9f
        
        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childMidpoint = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2f
            val d = d1.coerceAtLeast(1f - abs(midpoint - childMidpoint) / midpoint * 0.3f)
            
            child.scaleX = d
            child.scaleY = d

            val distanceFromCenter = abs(midpoint - childMidpoint)
            val maxDistance = width / 2f
            val elevationRatio = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)
            child.translationY = elevationRatio * -40f
        }
    }
}