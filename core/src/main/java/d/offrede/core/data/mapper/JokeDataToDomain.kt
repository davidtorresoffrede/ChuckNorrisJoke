package d.offrede.core.data.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.core.data.model.JokeData
import d.offrede.core.domain.model.JokeDomain

object JokeDataToDomain : BaseMapper<JokeData, JokeDomain>() {
    override fun transform(source: JokeData) = JokeDomain(
        source.categories,
        source.iconUrl,
        source.url,
        source.text
    )
}