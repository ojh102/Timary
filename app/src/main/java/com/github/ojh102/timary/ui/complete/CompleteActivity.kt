package com.github.ojh102.timary.ui.complete

import android.os.Bundle
import android.view.animation.*
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityCompleteBinding
import com.github.ojh102.timary.util.Navigator
import com.github.ojh102.timary.util.extension.afterMeasured
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_complete.*
import java.util.concurrent.TimeUnit

class CompleteActivity : BaseActivity<ActivityCompleteBinding, CompleteContract.CompleteViewModel>() {

    override fun getLayoutRes() = R.layout.activity_complete

    override fun getModelClass() = CompleteContract.CompleteViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeAnimation()

        bind(
                viewModel.outputs.title()
                        .subscribe(binding::setTitle),

                viewModel.outputs.description()
                        .subscribe(binding::setDescription),

                viewModel.outputs.type()
                        .delay(3, TimeUnit.SECONDS)
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy {
                            if (it == CompleteType.SIGN_UP || it == CompleteType.WRITE) {
                                Navigator.navigateToMainActivity(this)
                            }
                            finish()
                        }
        )

    }

    private fun initializeAnimation() {
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

        tv_title.animation = titleAnimationSet
        tv_description.animation = descriptionAnimationSet

        titleAnimationSet.start()
        descriptionAnimationSet.start()

        iv_logo.afterMeasured({
            animation = ScaleAnimation(
                    1f, 0.8f, 1f, 0.8f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
                repeatCount = Animation.INFINITE
                repeatMode = Animation.REVERSE
                startOffset = 0
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
            }
            animation.start()
        })

    }

    override fun onBackPressed() {
        return
    }

}