package d.offrede.base.domain.usecase

import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

class BaseResultTest {
    @Test
    fun `test base result success`() {
        assertEquals(1, BaseResult.Success(1).data)
    }

    @Test
    fun `test base result error`() {
        assertEquals("error", BaseResult.Error(Exception("error")).exception.message)
    }
}