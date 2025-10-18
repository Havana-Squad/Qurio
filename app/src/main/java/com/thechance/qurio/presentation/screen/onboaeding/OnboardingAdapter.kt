package com.thechance.qurio.presentation.screen.onboaeding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.presentation.model.OnboardingPage
import com.thechance.qurio.presentation.screen.results.custom.OutlineTextView

class OnboardingAdapter(private val pages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding_page, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OnboardingViewHolder,
        position: Int
    ) {
        holder.bind(pages[position])
    }

    override fun getItemCount() = pages.size


    class OnboardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.onboardingImage)
        private val title = view.findViewById<TextView>(R.id.onboardingTitle)
        private val description = view.findViewById<TextView>(R.id.onboardingDescription)
        private val outlineTitle = view.findViewById<OutlineTextView>(R.id.WelcomeOutlineTextView)

        fun bind(page: OnboardingPage) {
            image.setImageResource(page.imageRes)
            title.setText(page.title)
            description.setText(page.description)
            outlineTitle.setText(page.title)
        }
    }
}

