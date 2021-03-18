package d.offrede.random_joke.data.repository

import d.offrede.base.domain.usecase.Network
import d.offrede.base.domain.usecase.Unexpected
import d.offrede.core.data.mapper.JokeDataToDomain
import d.offrede.core.data.model.JokeData
import d.offrede.random_joke.data.service.GetRandomJokeApi
import d.offrede.random_joke.domain.repository.GetRandomJokeRepository

class GetRandomJokeRepositoryImp(private val service: GetRandomJokeApi) : GetRandomJokeRepository {
    @Throws(Exception::class)
    override suspend fun getRandomJoke() = service.getRandomJoke().run {
        when(isSuccessful) {
            true -> mappingBody(body())
            else -> throw Network
        }
    }

    @Throws(Exception::class)
    private fun mappingBody(body: JokeData?) = body?.let { source ->
        JokeDataToDomain.transform(source)
    } ?: run {
        throw Unexpected
    }
}