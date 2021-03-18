package d.offrede.random_joke.domain.repository

import d.offrede.core.domain.model.JokeDomain

interface GetRandomJokeRepository {
    @Throws(Exception::class)
    suspend fun getRandomJoke(): JokeDomain
}