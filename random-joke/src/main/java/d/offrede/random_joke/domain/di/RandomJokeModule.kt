package d.offrede.random_joke.domain.di

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.random_joke.domain.model.JokeDomain
import d.offrede.random_joke.domain.usecase.GetRandomJokeUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val randomJokeDomainModule = module {
    factory<BaseUseCase<JokeDomain, Nothing>>(named("GetRandomJokeUseCase")) {
        GetRandomJokeUseCase(get())
    }
}
