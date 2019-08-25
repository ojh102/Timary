package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.util.extension.toPx
import com.github.ojh102.timary.util.extension.toast
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.cursor
import kotlinx.android.synthetic.main.fragment_home.tv_date
import javax.inject.Inject

internal class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes = R.layout.fragment_home

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    @Inject
    lateinit var homeAdapter: HomeAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initRecyclerView()
        initObserve()

        viewModel.loadCapsules()
    }

    private fun initObserve() {
        viewModel.homeItems.observe(this) { homeAdapter.submitList(it) }
        viewModel.toast.observe(this, EventObserver { context.toast(it) })
        viewModel.navDirections.observe(this, EventObserver { navController.navigate(it) })
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

        homeAdapter.setCallbacks(object : HomeAdapter.Callbacks {
            override fun onClickOpenedCapsule(capsule: Capsule) {
                viewModel.onClickOpenedCapsule(capsule)
            }

            override fun onClickClosedCapsule(capsule: Capsule) {
                viewModel.onClickClosedCapsule(capsule)
            }
        })
    }
}
