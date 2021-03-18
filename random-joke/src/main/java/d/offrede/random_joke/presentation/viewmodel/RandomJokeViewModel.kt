package d.offrede.random_joke.presentation.viewmodel

import android.util.Log
import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.base.presentation.model.ViewModelResult.Success
import d.offrede.base.presentation.model.VisibilityStatus
import d.offrede.base.presentation.viewmodel.BaseLiveData
import d.offrede.base.presentation.viewmodel.BaseViewModel
import d.offrede.core.domain.model.JokeDomain
import d.offrede.core.presentation.mapper.JokeDomainToView
import d.offrede.core.presentation.model.Joke
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RandomJokeViewModel(
    private val getRandomJokeUseCase: BaseUseCase<JokeDomain, Unit>
) : BaseViewModel() {

    private val resultLiveData = BaseLiveData<Success<Joke>>()

    fun resultLiveData() = resultLiveData

    fun getJoke() {
        jobs add launch {
            showLoading()
            withContext(IO) {
                getRandomJokeUseCase.run(
                    onSuccess = ::makeSuccess,
                    onFailure = ::makeFailure
                )
            }
            hideLoading()
        }
    }

    private fun makeSuccess(jokeDomain: JokeDomain) {
        showSuccess(JokeDomainToView.transform(jokeDomain))
        hideEmpty()
        hideLoading()
        hideFailure()
    }

    private fun makeFailure(failureResult: Exception) {
        Log.e("failureResult", failureResult.message ?: "", failureResult)
        showFailure()
    }

    private fun showSuccess(joke: Joke) {
        this.resultLiveData.postValue(VisibilityStatus.VISIBLE to Success(joke))
    }

    private fun hideSuccess() {
        this.resultLiveData.postValue(VisibilityStatus.GONE to Success(Joke()))
    }

    override fun showEmpty(message: String) {
        super.showEmpty(message)
        hideSuccess()
    }

    override fun showFailure(message: String) {
        super.showFailure(message)
        hideSuccess()
    }

    override fun showLoading(message: String) {
        super.showLoading(message)
        hideSuccess()
    }

}