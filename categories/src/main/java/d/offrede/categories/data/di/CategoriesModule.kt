package d.offrede.categories.data.di

import d.offrede.categories.data.repository.CategoriesRepositoryImp
import d.offrede.categories.data.service.CategoriesApi
import d.offrede.categories.domain.repository.CategoriesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val categoriesDataModule = module {
    factory<CategoriesApi>(named("CategoriesApi")) {
        get<Retrofit>().create(CategoriesApi::class.java)
    }

    factory<CategoriesRepository> {
        CategoriesRepositoryImp(get(named("CategoriesApi")))
    }
}