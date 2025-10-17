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
        setupListeners()
    }

    override fun onClickRightArrow() {
        val pager = binding.viewPager
        val lastIndex = pager.adapter?.itemCount?.minus(1) ?: return

        if (pager.currentItem < lastIndex) {
            pager.currentItem++
            return
        }

        if (pager.currentItem == lastIndex) {
            presenter.firstAppLaunch()
        }
    }

    override fun onClickLeftArrow() {
        val pager = binding.viewPager
        if (pager.currentItem > 0) pager.currentItem--
    }

    override fun onSwipeUp() {
        presenter.firstAppLaunch()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            adapter = OnboardingAdapter(OnboardingPage.getOnboardingPages())
            isUserInputEnabled = false
        }
    }

    private fun setupListeners() {
        binding.rightArrow.setOnClickListener { onClickRightArrow() }
        binding.leftArrow.setOnClickListener { onClickLeftArrow() }
    }
}