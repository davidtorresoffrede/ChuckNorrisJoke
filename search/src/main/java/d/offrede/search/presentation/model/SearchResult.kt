package d.offrede.search.presentation.model

import d.offrede.core.presentation.model.Joke

data class SearchResult(
    val total: String = "",
    val jokes: List<Joke> = listOf()
)