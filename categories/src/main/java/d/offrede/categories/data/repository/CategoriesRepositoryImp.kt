package d.offrede.categories.data.repository

import d.offrede.base.domain.usecase.Network
import d.offrede.base.domain.usecase.Unexpected
import d.offrede.categories.data.service.CategoriesApi
import d.offrede.categories.domain.repository.CategoriesRepository
import java.lang.Exception

class CategoriesRepositoryImp(private val service: CategoriesApi) : CategoriesRepository {
    @Throws(Exception::class)
    override suspend fun getCategories() = service.getCategories().run {
        when(isSuccessful) {
            true -> mappingBody(body())
            else -> throw Network
        }
    }

    @Throws(Exception::class)
    private fun mappingBody(body: List<String>?) = body ?: throw Unexpected
}