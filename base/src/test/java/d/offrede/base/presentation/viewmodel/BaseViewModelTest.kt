package d.offrede.base.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import d.offrede.base.presentation.model.ViewModelResult
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {

    private lateinit var testViewModel: BaseViewModel

    private lateinit var failureLiveData: BaseLiveData<ViewModelResult.Failure>
    private lateinit var emptyLiveData: BaseLiveData<ViewModelResult.Empty>
    private lateinit var loadingLiveData: BaseLiveData<ViewModelResult.Loading>

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun beforeTests() {
        testViewModel = spyk(recordPrivateCalls = true)

        failureLiveData = mockk(relaxed = true)
        emptyLiveData = mockk(relaxed = true)
        loadingLiveData = mockk(relaxed = true)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `show failure`() {
        testViewModel.showFailure()

        verify {
            testViewModel.hideEmpty()
            testViewModel.hideLoading()
            testViewModel["showFailure"](any<ViewModelResult.Failure>())
        }
    }

    @Test
    fun `hide failure`() {
        testViewModel.hideFailure()

        verify {
            testViewModel["hideFailure"](any<ViewModelResult.Failure>())
        }
    }

    @Test
    fun `show empty`() {
        testViewModel.showEmpty()

        verify {
            testViewModel.hideFailure()
            testViewModel.hideLoading()
            testViewModel["showEmpty"](any<ViewModelResult.Empty>())
        }
    }

    @Test
    fun `hide empty`() {
        testViewModel.hideEmpty()

        verify {
            testViewModel["hideEmpty"](any<ViewModelResult.Empty>())
        }
    }

    @Test
    fun `show loading`() {
        testViewModel.showLoading()

        verify {
            testViewModel.hideFailure()
            testViewModel.hideEmpty()
            testViewModel["showLoading"](any<ViewModelResult.Loading>())
        }
    }

    @Test
    fun `hide loading`() {
        testViewModel.hideLoading()

        verify {
            testViewModel["hideLoading"](any<ViewModelResult.Loading>())
        }
    }

}