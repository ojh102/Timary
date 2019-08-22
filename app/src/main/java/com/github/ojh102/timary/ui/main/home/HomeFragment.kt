package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import com.github.ojh102.timary.util.extension.toPx
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.cursor

internal class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes = R.layout.fragment_home

    override val navController: NavController
        get() = activity!!.findNavController(R.id.nav_host_fragment)

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initRecyclerView()

        viewModel.navigateToWrite.observe(this, EventObserver {
            navController.navigate(it)
        })
    }

    private fun initToolbar() {
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, _ ->
            val offsetPercentage = (1 - (appBarLayout.y / appBarLayout.totalScrollRange) * -1)

            binding.tvDate.alpha = offsetPercentage

            val bottomMargin = 16.toPx * offsetPercentage

            val layoutParams = binding.tvWrite.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.bottomMargin = bottomMargin.toInt()
            binding.tvWrite.layoutParams = layoutParams
        })

        cursor.run {
            animation = AlphaAnimation(0f, 1f).apply {
                repeatCount = Animation.INFINITE
                repeatMode = Animation.REVERSE
                startOffset = 300
                duration = 300
            }
            animation.start()
        }
    }

    private fun initRecyclerView() {

    }
}
