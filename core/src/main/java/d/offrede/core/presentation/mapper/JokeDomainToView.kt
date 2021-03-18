package d.offrede.core.presentation.mapper

import d.offrede.base.domain.mapper.BaseMapper
import d.offrede.core.domain.model.JokeDomain
import d.offrede.core.presentation.model.Joke

object JokeDomainToView : BaseMapper<JokeDomain, Joke>() {
    override fun transform(source: JokeDomain) = Joke(
        categories = source.categories ?: listOf(),
        iconUrl = source.iconUrl ?: "",
        url = source.url ?: "",
        text = source.text ?: ""
    )
}