package d.offrede.base.presentation.model

sealed class ViewModelResult<out T : Any> {
    class Success<out T : Any>(val data: T): ViewModelResult<T>()
    class Failure(val message: String = ""): ViewModelResult<String>()
    class Empty(val message: String = ""): ViewModelResult<String>()
    class Loading(val message: String = ""): ViewModelResult<String>()
}
