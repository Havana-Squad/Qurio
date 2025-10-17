package com.thechance.qurio.presentation.screen.home

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentHomeBinding
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.model.GameUi
import com.thechance.qurio.presentation.screen.achievements.AchievementsDialogFragment
import com.thechance.qurio.presentation.screen.buylife.BuyLifeDialogFragment
import com.thechance.qurio.presentation.screen.characters.CharacterDialogFragment
import com.thechance.qurio.presentation.screen.difficulty.DifficultyLevelDialogFragment
import com.thechance.qurio.presentation.screen.games_screen.GameItem
import com.thechance.qurio.presentation.screen.home.adapter.GamesPagerAdapter
import com.thechance.qurio.presentation.screen.played_games_screen.PlayedGamesAdapter
import com.thechance.qurio.presentation.screen.settings.SettingsDialogFragment
import jakarta.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView{

    override val layoutIdFragment: Int
        get() = R.layout.fragment_home

    private val gamesAdapter: GamesPagerAdapter by lazy { GamesPagerAdapter(this) }
    @Inject
    override lateinit var presenter: HomePresenter

    override fun setupViews() {
        setupCharacterButton()
        setupSettingsButton()
        setupBuyLivesButton()
        setupAwardsButton()
        setupAllGamesButton()
        setupGamesRecyclerView()
        setupAllLastGamesButton()
    }

    private fun setupCharacterButton() {
        binding.imageSelectedCharacter.setOnClickListener {
            CharacterDialogFragment().show(childFragmentManager, "CharacterDialog")
        }
    }

    private fun setupSettingsButton() {
        binding.buttonSettings.setOnClickListener {
            SettingsDialogFragment().show(childFragmentManager, "SettingsDialog")
        }
    }

    private fun setupBuyLivesButton() {
        binding.btnBuyLives.setOnClickListener {
            BuyLifeDialogFragment().show(childFragmentManager, "BuyLifeDialog")
        }
    }


    private fun setupAwardsButton() {
        binding.btnUpgradeAwards.setOnClickListener {
            AchievementsDialogFragment().show(childFragmentManager, "AchievementsDialog")
        }
    }

    private fun setupAllGamesButton() {
        binding.allGamesContainer.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }
    }

    private fun setupAllLastGamesButton() {
        binding.lastGamesAllContainer.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPlayedGamesFragment())
        }
    }

    private fun setupGamesRecyclerView() {
        val adapter = gamesAdapter
        binding.gamesPager.adapter = adapter
        binding.gamesPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.gamesPager.offscreenPageLimit = 3

        binding.gamesPager.setPageTransformer(createGameCardTransformer())

        (binding.gamesPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
    }

    fun createGameCardTransformer(): ViewPager2.PageTransformer {
        return ViewPager2.PageTransformer { page, position ->
            val absPosition = kotlin.math.abs(position)


            page.scaleY = 1f
            page.scaleX = 1f


            page.rotationY = when {
                absPosition < 0.1f -> 0f
                else -> -position * 4f
            }

            page.rotationX = when {
                absPosition < 0.1f -> 0f
                else -> absPosition * 25f
            }

            page.translationX = -position * page.width * 0.05f
            page.translationY = absPosition * page.height * 0.05f

            page.alpha = when {
                absPosition >= 3f -> 0f
                else -> 1f
            }

            page.elevation = 0f

            page.translationZ = 0f

            page.cameraDistance = page.width * 15f
        }
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

    override fun setGames(games: List<GameItem>) {
        gamesAdapter.setItems(games)
    }

    override fun setUserLastGames(lastGames: List<GameUi>) {
        val adapter = PlayedGamesAdapter(lastGames)
        binding.lastGamesRecyclerview.adapter = adapter
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

    override fun navigateToGame(gameId: Int) {
        DifficultyLevelDialogFragment.newInstance {
            val action = HomeFragmentDirections.actionHomeFragmentToStartPlayFragment(categoryId = gameId)
            findNavController().navigate(action)
        }.show(childFragmentManager, "DifficultyLevelDialog")
    }
}