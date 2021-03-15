package d.offrede.base.presentation.model

import org.junit.Test

class ViewModelResultTest {

    @Test
    fun `success data string`() {
        val success = ViewModelResult.Success("success")

        assert(success.data == "success")
    }

    @Test
    fun `failure message empty`() {
        val failure = ViewModelResult.Failure()

        assert(failure.message == "")
    }

    @Test
    fun `failure message not empty`() {
        val failure = ViewModelResult.Failure("failure")

        assert(failure.message == "failure")
    }

    @Test
    fun `empty message empty`() {
        val empty = ViewModelResult.Empty()

        assert(empty.message == "")
    }

    @Test
    fun `empty message not empty`() {
        val empty = ViewModelResult.Empty("empty")

        assert(empty.message == "empty")
    }

    @Test
    fun `loading message empty`() {
        val loading = ViewModelResult.Loading()

        assert(loading.message == "")
    }

    @Test
    fun `loading message not empty`() {
        val loading = ViewModelResult.Loading("loading")

        assert(loading.message == "loading")
    }
}