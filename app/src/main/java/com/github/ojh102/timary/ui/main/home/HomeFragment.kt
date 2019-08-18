package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.LegacyBaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.Navigator
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.dpToPixel
import com.github.ojh102.timary.util.extension.toast
import com.google.android.material.appbar.AppBarLayout
import io.reactivex.rxkotlin.subscribeBy
import java.util.Date
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_home.appbar
import kotlinx.android.synthetic.main.fragment_home.cursor
import kotlinx.android.synthetic.main.fragment_home.rv_capsule
import kotlinx.android.synthetic.main.fragment_home.tv_date
import kotlinx.android.synthetic.main.fragment_home.tv_write

class HomeFragment : LegacyBaseFragment<FragmentHomeBinding, HomeContract.HomeViewModel>() {

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

        homeAdapter.setCallbacks(object : HomeAdapter.Callbacks {
            override fun onClickClosedCapsule(capsule: Capsule) {
                viewModel.inputs.onClickClosedCapsule(capsule)
            }

            override fun onClickOpenedCapsule(capsule: Capsule) {
                viewModel.inputs.onClickOpenedCapsule(capsule)
            }
        })
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.homeItemList()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy(
                                onNext = {
                                    homeAdapter.submitList(it)
                                }
                        ),

                viewModel.outputs.clickWrite()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy {
                            Navigator.navigateToWriteActivity(context)
                        },

                viewModel.outputs.toast()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy {
                            context?.toast(it)
                        },

                viewModel.outputs.navigateToRead()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy { capsuleId ->
                            context?.let {
                                Navigator.navigateToReadActivity(it, capsuleId)
                            }
                        },

                viewModel.outputs.navigateToWrite()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy {
                            context?.let { context ->
                                Navigator.navigateToWriteActivity(context)
                            }
                        }
        )
    }
}
