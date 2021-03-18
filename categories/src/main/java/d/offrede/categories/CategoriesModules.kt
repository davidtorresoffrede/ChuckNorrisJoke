package d.offrede.categories

import d.offrede.categories.data.di.categoriesDataModule
import d.offrede.categories.domain.di.categoriesDomainModule
import d.offrede.categories.presentation.di.categoriesModule
import org.koin.core.module.Module

val categoriesModules = listOf<Module>(
    categoriesDataModule,
    categoriesDomainModule,
    categoriesModule
)