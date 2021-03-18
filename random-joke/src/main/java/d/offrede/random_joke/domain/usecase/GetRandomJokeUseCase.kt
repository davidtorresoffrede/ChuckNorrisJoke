package d.offrede.random_joke.domain.usecase

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.core.domain.model.JokeDomain
import d.offrede.random_joke.domain.repository.GetRandomJokeRepository

class GetRandomJokeUseCase(
    private val repository: GetRandomJokeRepository
) : BaseUseCase<JokeDomain, Nothing>() {
    override suspend fun process(
        params: Nothing?,
        onSuccess: (JokeDomain) -> Unit,
        onFailure: (Exception) -> Unit
    ) = onSuccess(repository.getRandomJoke())
}