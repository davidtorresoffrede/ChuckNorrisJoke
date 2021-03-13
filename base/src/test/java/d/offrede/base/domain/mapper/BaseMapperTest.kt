package d.offrede.base.domain

import d.offrede.base.domain.mapper.BaseMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseMapperTest {

    @Test
    fun `test base mapper implementation for int to string`() {
        assertEquals("1", TestBaseMapper.transform(1))
    }

}

object TestBaseMapper : BaseMapper<Int, String>() {
    override fun transform(source: Int) = source.toString()
}