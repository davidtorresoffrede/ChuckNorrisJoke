package d.offrede.search.domain.model

import d.offrede.core.domain.model.JokeDomain

data class SearchResultDomain(
    val total: Int? = null,
    val jokes: List<JokeDomain?>? = null
)
