package com.github.ojh102.timary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ojh102.timary.FakeLocalRepository
import com.github.ojh102.timary.MainCoroutineRule
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.home.HomeItems
import com.github.ojh102.timary.ui.home.HomeViewModel
import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.LiveDataTestUtil.getValue
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
import java.time.LocalDate

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class HomeViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockResourcesProvider: ResourcesProvider

    private val capsule = Capsule(
        "",
        LocalDate.now(),
        LocalDate.now()
    )

    private val fakeLocalRepository: LocalRepository = FakeLocalRepository()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        `when`(mockResourcesProvider.getString(anyInt())).thenReturn("")

        homeViewModel = HomeViewModel(
            mockResourcesProvider,
            fakeLocalRepository
        )
    }

    @Test
    fun `데이터 로드 후 오늘날짜 표시`() {
        homeViewModel.loadCapsules()

        assertNotNull(getValue(homeViewModel.today))
    }

    @Test
    fun `데이터 로드 후 홈아이템 여부`() {
        homeViewModel.loadCapsules()

        assertNotNull(getValue(homeViewModel.homeItems))
    }

    @Test
    fun `글쓰기 클릭시 글쓰기 화면으로 이동`() {
        homeViewModel.onClickWrite()

        assertNotNull(getValue(homeViewModel.navDirections))
    }

    @Test
    fun `열린캡슐 클릭시 읽기 화면으로 이동`() {
        homeViewModel.onClickOpenedCapsule(HomeItems.StoredCapsule.OpenedCapsule(capsule))

        assertNotNull(getValue(homeViewModel.navDirections))
    }

    @Test
    fun `닫힌캡슐 클릭시 토스트 출력`() {
        homeViewModel.onClickClosedCapsule(HomeItems.StoredCapsule.ClosedCapsule(capsule))

        assertNotNull(getValue(homeViewModel.toast))
    }
}
