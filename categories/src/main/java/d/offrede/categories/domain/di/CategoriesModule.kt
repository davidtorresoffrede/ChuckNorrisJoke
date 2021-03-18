package d.offrede.categories.domain.di

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.categories.domain.usecase.CategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val categoriesDomainModule = module {
    factory<BaseUseCase<List<String>, Nothing>>(named("CategoriesUseCase")) {
        CategoriesUseCase(get())
    }
}
