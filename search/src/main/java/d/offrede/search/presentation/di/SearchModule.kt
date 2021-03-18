package d.offrede.search.presentation.di

import d.offrede.search.presentation.view.SearchFragment
import d.offrede.search.presentation.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val searchModule = module {
    fragment {
        SearchFragment()
    }

    factory(named("SearchFragmentClass")) {
        SearchFragment::class.java
    }

    viewModel {
        SearchViewModel(get(named("SearchUseCase")))
    }
}