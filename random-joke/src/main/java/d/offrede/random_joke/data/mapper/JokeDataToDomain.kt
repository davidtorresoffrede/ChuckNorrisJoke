package d.offrede.random_joke.data.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.random_joke.data.model.JokeData
import d.offrede.random_joke.domain.model.JokeDomain

object JokeDataToDomain : BaseMapper<JokeData, JokeDomain>() {
    override fun transform(source: JokeData) = JokeDomain(
        source.categories,
        source.iconUrl,
        source.url,
        source.text
    )
}