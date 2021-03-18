package d.offrede.base.domain.usecase


abstract class BaseUseCase<Type, in Params> where Type : Any {
    suspend fun run(
        params: Params? = null,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    ) {
        try {
            process(params, onSuccess, onFailure)
        } catch (e: Exception) {
            onFailure(Network)
        }
    }

    abstract suspend fun process(
        params: Params?,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    )
}