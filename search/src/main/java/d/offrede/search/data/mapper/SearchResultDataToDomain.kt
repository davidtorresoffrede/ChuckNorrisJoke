package d.offrede.search.data.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.core.data.mapper.JokeDataToDomain
import d.offrede.core.domain.model.JokeDomain
import d.offrede.search.data.model.SearchResultData
import d.offrede.search.domain.model.SearchResultDomain

object SearchResultDataToDomain : BaseMapper<SearchResultData, SearchResultDomain>() {
    override fun transform(source: SearchResultData) = SearchResultDomain(
        total = source.total,
        jokes = source.jokes?.let { listJokesData ->
            val listJokesDomain = mutableListOf<JokeDomain>()
            listJokesData.forEach { jokeData ->
                jokeData?.let {
                    listJokesDomain.add(JokeDataToDomain.transform(it))
                }
            }
            listJokesDomain.toList()
        }
    )
}