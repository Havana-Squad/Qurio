package com.thechance.qurio.presentation.screen.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentHomeBinding
import com.thechance.qurio.presentation.base.BaseFragment
import jakarta.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_home

    @Inject
    override lateinit var presenter: HomePresenter

    override fun setupViews() {
        binding.buttonSettings.setOnClickListener { onSettingsClicked() }
    }

    override fun setUserCharacter(character: String) {
        binding.imageSelectedCharacter.setImageDrawable(getDrawable(R.drawable.rika))
        binding.textCharacterName.text = character
    }

    override fun setUserStatistics(statistics: Triple<Int, Int, Int>) {
        binding.livesCount.text = statistics.first.toString()
        binding.pointsCount.text = statistics.second.toString()
        binding.awardsCount.text = statistics.third.toString()
        if (statistics.second >= 10000) {
           binding.pointsCrown.visibility = View.VISIBLE
        }
    }

    override fun setUserStreak(streak: Int) {
        //set title
        if(streak == 0) {
            getString(R.string.cancel)
        } else {
            getString(R.string.cancel)
        }
        //set subtitle
        //set streaks
    }

    override fun setGames(games: List<String>) {
        TODO("Not yet implemented")
    }

    override fun setUserLastGames(lastGames: List<String>) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(message: String) {
        TODO("Not yet implemented")
    }

    private fun getDrawable(resId: Int): Drawable? {
        return AppCompatResources.getDrawable(context ?: requireContext(), resId)
    }

    private fun onSettingsClicked(){
        //todo: show settings dialog
    }
}