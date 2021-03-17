package d.offrede.random_joke.presentation.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.random_joke.domain.model.JokeDomain
import d.offrede.random_joke.presentation.model.Joke

object JokeDomainToView : BaseMapper<JokeDomain, Joke>() {
    override fun transform(source: JokeDomain) = Joke(
        categories = source.categories ?: listOf(),
        iconUrl = source.iconUrl ?: "",
        url = source.url ?: "",
        text = source.text ?: ""
    )
}