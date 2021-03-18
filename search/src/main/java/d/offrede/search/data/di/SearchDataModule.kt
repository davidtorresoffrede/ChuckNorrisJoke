package d.offrede.search.data.di

import d.offrede.search.data.repository.SearchRepositoryImp
import d.offrede.search.data.service.SearchApi
import d.offrede.search.domain.repository.SearchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val searchDataModule = module {
    factory<SearchApi>(named("SearchApi")) {
        get<Retrofit>().create(SearchApi::class.java)
    }

    factory<SearchRepository> {
        SearchRepositoryImp(get(named("SearchApi")))
    }
}