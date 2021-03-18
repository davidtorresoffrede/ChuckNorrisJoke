package d.offrede.search

import d.offrede.search.data.di.searchDataModule
import d.offrede.search.domain.di.searchDomainModule
import d.offrede.search.presentation.di.searchModule

val searchModules = listOf(
    searchDataModule,
    searchDomainModule,
    searchModule
)