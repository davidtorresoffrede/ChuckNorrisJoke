package d.offrede.search.data.repository

import d.offrede.base.domain.usecase.Network
import d.offrede.base.domain.usecase.Unexpected
import d.offrede.search.data.mapper.SearchResultDataToDomain
import d.offrede.search.data.model.SearchResultData
import d.offrede.search.data.service.SearchApi
import d.offrede.search.domain.repository.SearchRepository
import java.lang.Exception

class SearchRepositoryImp(private val service: SearchApi) : SearchRepository {
    @Throws(Exception::class)
    override suspend fun search(query: String) = service.search(query).run {
        when(isSuccessful) {
            true -> mappingBody(body())
            else -> throw Network
        }
    }

    @Throws(Exception::class)
    private fun mappingBody(body: SearchResultData?) = body?.let { source ->
        SearchResultDataToDomain.transform(source)
    } ?: run {
        throw Unexpected
    }
}