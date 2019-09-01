package com.github.ojh102.timary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ojh102.timary.ui.splash.SplashViewModel
import com.github.ojh102.timary.utils.LiveDataTestUtil.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class SplashViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup(){
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `스플래쉬후 메인으로 이동`() {
        coroutineRule.pauseDispatcher()

        splashViewModel.initSplash()

        coroutineRule.resumeDispatcher()

        assertNotNull(getValue(splashViewModel.navDirections))
    }
}
