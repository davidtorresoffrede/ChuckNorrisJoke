package d.offrede.search.domain.di

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.search.domain.model.SearchResultDomain
import d.offrede.search.domain.usecase.SearchUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchDomainModule = module {
    factory<BaseUseCase<SearchResultDomain, String>>(named("SearchUseCase")) {
        SearchUseCase(get())
    }
}