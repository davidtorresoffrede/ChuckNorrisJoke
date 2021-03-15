package d.offrede.base.domain.usecase

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class BaseUseCaseTest {

    private lateinit var testUseCase: BaseUseCase<String, Int>

    private lateinit var onSuccess: (String) -> Unit
    private lateinit var onFailure: (BaseFailureResult) -> Unit

    @Before
    fun beforeTests() {
        testUseCase = spyk(recordPrivateCalls = true)

        onSuccess = mockk(relaxed = true)
        onFailure = mockk(relaxed = true)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `test base use case run success`() = runBlocking {
        every {
            testUseCase["process"](
                1,
                onSuccess,
                onFailure
            )
        } answers {
            secondArg<(String) -> Unit>().invoke("")
        }

        testUseCase.run(1, onSuccess, onFailure)

        coVerifySequence {
            testUseCase.run(any(), any(), any())
            testUseCase["process"](any<Int>(), any<(String) -> Unit>(), any<(BaseFailureResult) -> Unit>())
            onSuccess(any())
        }
    }

    @Test
    fun `test base use case run failed`() = runBlocking {
        every {
            testUseCase["process"](
                1,
                onSuccess,
                onFailure
            )
        } answers {
            thirdArg<(BaseFailureResult) -> Unit>().invoke(BaseFailureResult.Unexpected)
        }

        testUseCase.run(1, onSuccess, onFailure)

        coVerifySequence {
            testUseCase.run(any(), any(), any())
            testUseCase["process"](any<Int>(), any<(String) -> Unit>(), any<(BaseFailureResult) -> Unit>())
            onFailure(BaseFailureResult.Unexpected)
        }
    }

    @Test
    fun `test base use case run exception`() = runBlocking {
        every {
            testUseCase["process"](
                1,
                onSuccess,
                onFailure
            )
        } throws Exception("")


        testUseCase.run(1, onSuccess, onFailure)

        coVerifySequence {
            testUseCase.run(any(), any(), any())
            testUseCase["process"](any<Int>(), any<(String) -> Unit>(), any<(BaseFailureResult) -> Unit>())
            onFailure(BaseFailureResult.Network)
        }
    }
}