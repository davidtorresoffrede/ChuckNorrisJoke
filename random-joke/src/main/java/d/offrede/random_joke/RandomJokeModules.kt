package d.offrede.random_joke

import d.offrede.random_joke.data.di.randomJokeDataModule
import d.offrede.random_joke.domain.di.randomJokeDomainModule
import d.offrede.random_joke.presentation.di.randomJokeModule

val randomJokeModules = listOf(
    randomJokeDataModule,
    randomJokeDomainModule,
    randomJokeModule
)