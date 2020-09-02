package com.github.ojh102.timary.ui.home

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.util.extension.toast
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.cursor
import kotlin.reflect.KClass

@AndroidEntryPoint
internal class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    private val homeAdapter by lazy { HomeAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initRecyclerView()
        initNavigation()
        initToast()

        viewModel.loadCapsules()
    }

    private fun initToolbar() {
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, _ ->
            binding.tvDate.alpha = (1 - (appBarLayout.y / appBarLayout.totalScrollRange) * -1)
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
        binding.rvCapsule.run {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        viewModel.homeItems.observe(this) { homeAdapter.submitList(it) }
    }

    private fun initNavigation() {
        viewModel.navDirections.observe(this, EventObserver { navController.navigate(it) })
    }

    private fun initToast() {
        viewModel.toast.observe(this, EventObserver { context.toast(it) })
    }
}
