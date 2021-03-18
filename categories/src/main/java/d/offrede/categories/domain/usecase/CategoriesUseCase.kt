package d.offrede.categories.domain.usecase

import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.categories.domain.repository.CategoriesRepository

class CategoriesUseCase(
    private val repository: CategoriesRepository
) : BaseUseCase<List<String>, Nothing>() {
    override suspend fun process(
        params: Nothing?,
        onSuccess: (List<String>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        onSuccess(repository.getCategories())
    }
}