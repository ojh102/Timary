package com.github.ojh102.timary.ui.splash

import android.os.Bundle
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivitySplashBinding
import com.github.ojh102.timary.util.intent.Navigator
import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashContract.SplashViewModel>() {

    override fun getLayoutRes() = R.layout.activity_splash
    override fun getModelClass() = SplashContract.SplashViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindObservable()
    }

    private fun bindObservable() {
        bind(
                Completable.timer(2, TimeUnit.SECONDS)
                        .subscribeBy {
                            Navigator.navigateToMainActivity(this)
                            finish()
                        }
        )
    }

}
