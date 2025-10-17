package com.thechance.qurio.presentation.screen.onboaeding

import android.os.Bundle
import android.view.View
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentOnboardingBinding
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.model.OnboardingPage
import javax.inject.Inject

class OnboardingFragment :
    BaseFragment<FragmentOnboardingBinding, OnboardingView, OnboardingPresenter>(),
    OnboardingView {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_onboarding

    @Inject
    override lateinit var presenter: OnboardingPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        setupViewPager()
    }
    override fun onClickRightArrow() {
        TODO("Not yet implemented")
    }

    override fun onClickLeftArrow() {
        TODO("Not yet implemented")
    }

    override fun onSwipeUp() {
        TODO("Not yet implemented")
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = OnboardingAdapter(OnboardingPage.getOnboardingPages())
            isUserInputEnabled = false
        }
    }
}