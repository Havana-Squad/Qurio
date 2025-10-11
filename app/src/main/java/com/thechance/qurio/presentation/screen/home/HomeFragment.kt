package com.thechance.qurio.presentation.screen.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        setupSettingsButton()
        setupBuyLivesButton()
        setupAwardsButton()
        setupAllGamesButton()
        setupAllLastGamesButton()
    }

    private fun setupSettingsButton() {
        TODO("Show settings dialog")
    }

    private fun setupBuyLivesButton() {
        TODO("navigate to buy lives screen")
    }


    private fun setupAwardsButton() {
        TODO("Not yet implemented")
    }

    private fun setupAllGamesButton() {
        TODO("navigate to games screen")
    }

    private fun setupAllLastGamesButton() {
        TODO("navigate to last games screen")
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
        if(streak == 0) {
            binding.streakDay.text = getString(R.string.zero_streak_title)
            binding.streakSubtitle.text = getString(R.string.zero_streak_subtitle)
        } else {
            binding.streakDay.text = getString(R.string.numbered_streak_title, streak)
            binding.streakSubtitle.text = getString(R.string.zero_streak_subtitle)

            val fireIcons = listOf(
                binding.sundayFire,
                binding.mondayFire,
                binding.tuesdayFire,
                binding.wednesdayFire,
                binding.thursdayFire,
                binding.fridayFire,
                binding.satCircleFire
            )

            fireIcons.forEach { it.visibility = View.INVISIBLE }

            fireIcons.take(streak.coerceIn(0, fireIcons.size)).forEach {
                it.visibility = View.VISIBLE
            }
        }
    }

    override fun setGames(games: List<String>) {
        TODO("Not yet implemented")
    }

    override fun setUserLastGames(lastGames: List<String>) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun getDrawable(resId: Int): Drawable? {
        return AppCompatResources.getDrawable(context ?: requireContext(), resId)
    }
}