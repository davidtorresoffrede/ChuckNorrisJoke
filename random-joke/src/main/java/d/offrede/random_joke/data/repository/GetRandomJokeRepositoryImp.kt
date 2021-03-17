package d.offrede.random_joke.data.repository

import d.offrede.base.domain.usecase.BaseFailureResult
import d.offrede.random_joke.data.mapper.JokeDataToDomain
import d.offrede.random_joke.data.model.JokeData
import d.offrede.random_joke.data.service.GetRandomJokeApi
import d.offrede.random_joke.domain.repository.GetRandomJokeRepository
import d.offrede.random_joke.presentation.di.randomJokeModule
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

class GetRandomJokeRepositoryImp(private val service: GetRandomJokeApi) : GetRandomJokeRepository {
    @Throws(BaseFailureResult::class)
    override suspend fun getRandomJoke() = service.getRandomJoke().run {
        when(isSuccessful) {
            true -> mappingBody(body())
            else -> throw BaseFailureResult.Network
        }
    }

    @Throws(BaseFailureResult::class)
    private fun mappingBody(body: JokeData?) = body?.let { source ->
        JokeDataToDomain.transform(source)
    } ?: run {
        throw BaseFailureResult.Unexpected
    }
}