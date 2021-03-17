package d.offrede.random_joke.domain.repository

import d.offrede.base.domain.usecase.BaseFailureResult
import d.offrede.random_joke.domain.model.JokeDomain

interface GetRandomJokeRepository {
    @Throws(BaseFailureResult::class)
    suspend fun getRandomJoke(): JokeDomain
}