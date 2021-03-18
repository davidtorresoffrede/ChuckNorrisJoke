package d.offrede.categories.presentation.di

import d.offrede.categories.presentation.view.CategoriesFragment
import d.offrede.categories.presentation.viewmodel.CategoriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val categoriesModule = module {
    fragment {
        CategoriesFragment()
    }

    factory(named("CategoriesFragmentClass")) {
        CategoriesFragment::class.java
    }

    viewModel {
        CategoriesViewModel(get(named("CategoriesUseCase")))
    }

}