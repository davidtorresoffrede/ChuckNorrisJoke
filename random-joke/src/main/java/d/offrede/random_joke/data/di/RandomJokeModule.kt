package d.offrede.random_joke.data.di

import d.offrede.random_joke.data.repository.GetRandomJokeRepositoryImp
import d.offrede.random_joke.data.service.GetRandomJokeApi
import d.offrede.random_joke.domain.repository.GetRandomJokeRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val randomJokeDataModule = module {
    factory<GetRandomJokeApi>(named("GetRandomJokeApi")) {
        get<Retrofit>().create(GetRandomJokeApi::class.java)
    }

    factory<GetRandomJokeRepository> {
        GetRandomJokeRepositoryImp(get(named("GetRandomJokeApi")))
    }
}