package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.intent.Navigator
import com.github.ojh102.timary.util.resources.TimaryResourcesUtil
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeContract.HomeViewModel>() {

    companion object {
        const val TAG = "home"

        fun newInstance() = HomeFragment()
    }

    @Inject
    protected lateinit var timaryParser: TimaryParser

    @Inject
    protected lateinit var homeAdapter: HomeAdapter

    override fun getLayoutRes() = R.layout.fragment_home
    override fun getModelClass() = HomeContract.HomeViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.inputs = viewModel

        initializeAppbar()
        initializeRecyclerView()

        bindObservable()
    }

    private fun initializeAppbar() {
        binding.today = timaryParser.dateToTitleWithLine(Date().time)

        appbar.addOnOffsetChangedListener { appBarLayout, _ ->
            val offsetPercentage = (1 - (appBarLayout.y / appBarLayout.totalScrollRange) * -1)

            tv_date.alpha = offsetPercentage

            val bottomMargin = TimaryResourcesUtil.dpToPixel(16f) * offsetPercentage

            val layoutParams = tv_write.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.bottomMargin = bottomMargin.toInt()
            tv_write.layoutParams = layoutParams
        }

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

    private fun initializeRecyclerView() {
        rv_capsule.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    private fun bindObservable() {

        viewModel.outputs.homeCapsuleList()
//                .subscribeBy(
//                        onNext = {
//                            homeAdapter.clear()
//                            homeAdapter.addItem(HomeHeaderItem(it.size))
//                            homeAdapter.addItems(it)
//                        }
//                )

        bind(
                viewModel.outputs.homeCapsuleList()
                        .subscribeBy(
                                onNext = {
                                    homeAdapter.clear()
                                    homeAdapter.addItem(HomeHeaderItem(it.size))
                                    homeAdapter.addItems(it)
                                }
                        ),

                viewModel.outputs.clickWrite()
                        .subscribeBy {
                            context?.let { Navigator.navigateToWriteActivity(it) }
                        }
        )
    }

}