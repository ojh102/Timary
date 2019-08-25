package com.github.ojh102.timary.ui.complete

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentCompleteBinding

internal class CompleteFragment : BaseFragment<FragmentCompleteBinding>() {
    override val layoutRes = R.layout.fragment_complete

    private val viewModel by viewModels<CompleteViewModel> { viewModelFactory }

    private val args by navArgs<CompleteFragmentArgs>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        viewModel.argument(args)
        viewModel.popup.observe(this, EventObserver { navController.popBackStack() })

        initAnimation()
    }

    private fun initAnimation() {
        val titleAlphaAnimation = AlphaAnimation(0f, 1f)
        val descAlphaAnimation = AlphaAnimation(0f, 1f)
        val titleTranslateAlphaAnimation = TranslateAnimation(0f, 0f, -50f, 0f)
        val descTranslateAlphaAnimation = TranslateAnimation(0f, 0f, -50f, 0f)

        val titleAnimationSet = AnimationSet(true).apply {
            addAnimation(titleAlphaAnimation)
            addAnimation(titleTranslateAlphaAnimation)
            startOffset = 600
            duration = 300
        }

        val descriptionAnimationSet = AnimationSet(true).apply {
            addAnimation(descAlphaAnimation)
            addAnimation(descTranslateAlphaAnimation)
            startOffset = 900
            duration = 300
        }

        binding.tvTitle.animation = titleAnimationSet
        binding.tvDescription.animation = descriptionAnimationSet

        titleAnimationSet.start()
        descriptionAnimationSet.start()

        binding.ivLogo.doOnPreDraw {
            binding.ivLogo.animation = ScaleAnimation(
                1f, 0.8f, 1f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                repeatCount = Animation.INFINITE
                repeatMode = Animation.REVERSE
                startOffset = 0
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
            }
            binding.ivLogo.animation.start()
        }
    }
}
