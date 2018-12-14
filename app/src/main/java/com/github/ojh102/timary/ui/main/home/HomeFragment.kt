package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.util.Navigator
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.dpToPixel
import com.google.android.material.appbar.AppBarLayout
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
    lateinit var timaryParser: TimaryParser

    @Inject
    lateinit var homeAdapter: HomeAdapter

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

        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, _ ->
            val offsetPercentage = (1 - (appBarLayout.y / appBarLayout.totalScrollRange) * -1)

            tv_date.alpha = offsetPercentage

            val bottomMargin = context.dpToPixel(16f) * offsetPercentage

            val layoutParams = tv_write.layoutParams as androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            layoutParams.bottomMargin = bottomMargin.toInt()
            tv_write.layoutParams = layoutParams
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

    private fun initializeRecyclerView() {
        rv_capsule.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.homeItemList()
                        .subscribeBy(
                                onNext = {
                                    homeAdapter.submitList(it)
                                }
                        ),

                viewModel.outputs.clickWrite()
                        .subscribeBy {
                            Navigator.navigateToWriteActivity(context)
                        }
        )
    }

}