package d.offrede.base.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import d.offrede.base.presentation.model.ViewModelResult
import d.offrede.base.presentation.model.VisibilityStatus
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BaseLiveDataTest {

    private lateinit var testLiveData: BaseLiveData<ViewModelResult<String>>

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun beforeTests() {
        testLiveData = BaseLiveData()
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `make visible`() {
        testLiveData.makeVisible(ViewModelResult.Success(""))
        assert(testLiveData.value!!.first == VisibilityStatus.VISIBLE)
    }

    @Test
    fun `make invisible`() {
        testLiveData.makeInvisible(ViewModelResult.Success(""))
        assert(testLiveData.value!!.first == VisibilityStatus.INVISIBLE)
    }

    @Test
    fun `make gone`() {
        testLiveData.makeGone(ViewModelResult.Success(""))
        assert(testLiveData.value!!.first == VisibilityStatus.GONE)
    }

}