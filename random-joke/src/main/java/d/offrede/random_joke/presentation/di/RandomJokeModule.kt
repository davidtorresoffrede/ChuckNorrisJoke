package d.offrede.random_joke.presentation.di

import d.offrede.base.presentation.view.BaseFragment
import d.offrede.random_joke.presentation.view.RandomJokeFragment
import d.offrede.random_joke.presentation.viewmodel.RandomJokeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val randomJokeModule = module {

    fragment {
        RandomJokeFragment()
    }

    factory(named("RandomJokeFragmentClass")) {
        RandomJokeFragment::class.java
    }

    viewModel {
        RandomJokeViewModel(get(named("GetRandomJokeUseCase")))
    }
}
