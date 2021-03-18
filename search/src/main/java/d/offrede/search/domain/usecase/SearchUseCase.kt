package d.offrede.search.domain.usecase

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.search.domain.model.SearchResultDomain
import d.offrede.search.domain.repository.SearchRepository

class SearchUseCase(
    private val repository: SearchRepository
) : BaseUseCase<SearchResultDomain, String>() {
    override suspend fun process(
        params: String?,
        onSuccess: (SearchResultDomain) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        params?.let { query ->
            onSuccess(repository.search(query))
        }
    }

}