package com.github.ojh102.timary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.home.HomeViewModel
import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.util.ResourcesUtil
import com.github.ojh102.timary.utils.FakeResoucesProvider
import com.github.ojh102.timary.utils.LiveDataTestUtil.getValue
import com.google.common.base.CharMatcher.any
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class HomeViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockLocalRepository: LocalRepository

    private val fakeResourcesProvider: ResourcesProvider = FakeResoucesProvider()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup(){
        homeViewModel = HomeViewModel(
            fakeResourcesProvider,
            mockLocalRepository
        )
    }
}
