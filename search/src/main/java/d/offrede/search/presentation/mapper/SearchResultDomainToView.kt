package d.offrede.search.presentation.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.core.presentation.mapper.JokeDomainToView
import d.offrede.core.presentation.model.Joke
import d.offrede.search.domain.model.SearchResultDomain
import d.offrede.search.presentation.model.SearchResult

object SearchResultDomainToView : BaseMapper<SearchResultDomain, SearchResult>() {
    override fun transform(source: SearchResultDomain) = SearchResult(
        total = source.total?.let { if (it > 0) it.toString() else "" } ?: "",
        jokes = source.jokes?.let { listJokesDomain ->
            val listJokes = mutableListOf<Joke>()
            listJokesDomain.forEach { jokeDomain ->
                jokeDomain?.let {
                    listJokes.add(JokeDomainToView.transform(it))
                }
            }
            listJokes.toList()
        } ?: listOf()
    )
}