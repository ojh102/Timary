package com.github.ojh102.timary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ojh102.timary.LiveDataTestUtil.getValue
import com.github.ojh102.timary.MainCoroutineRule
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.ui.write.content.WriteViewModel
import com.github.ojh102.timary.util.ResourcesProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class WriteViewModelTest {
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

    private lateinit var writeViewModel: WriteViewModel

    @Before
    fun setup() {
        `when`(mockResourcesProvider.getString(anyInt())).thenReturn("")

        writeViewModel = WriteViewModel(mockResourcesProvider)
    }

    @Test
    fun `담아두기 클릭시 빈 글자일땐 아무일도 안하기`() {
        writeViewModel.contentText.value = ""
        writeViewModel.onClickWrite()

        assertNull(getValue(writeViewModel.navigateToStore))
    }

    @Test
    fun `담아두기 클릭시 빈 글자일가 아닐시 저장화면으로 이동`() {
        writeViewModel.contentText.value = "content"
        writeViewModel.onClickWrite()

        assertNotNull(getValue(writeViewModel.navigateToStore))
    }

}
