package d.offrede.search.domain.repository

import d.offrede.search.domain.model.SearchResultDomain
import java.lang.Exception

interface SearchRepository {
    @Throws(Exception::class)
    suspend fun search(query: String) : SearchResultDomain
}