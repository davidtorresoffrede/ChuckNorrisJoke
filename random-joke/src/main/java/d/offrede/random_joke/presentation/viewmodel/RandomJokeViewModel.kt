package d.offrede.random_joke.presentation.viewmodel

import android.util.Log
import d.offrede.base.domain.usecase.BaseFailureResult
import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.base.presentation.model.ViewModelResult.Success
import d.offrede.base.presentation.model.VisibilityStatus
import d.offrede.base.presentation.viewmodel.BaseLiveData
import d.offrede.base.presentation.viewmodel.BaseViewModel
import d.offrede.random_joke.domain.model.JokeDomain
import d.offrede.random_joke.presentation.mapper.JokeDomainToView
import d.offrede.random_joke.presentation.model.Joke
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

    private fun makeFailure(failureResult: BaseFailureResult) {
        Log.e("failureResult", failureResult.message?:"", failureResult)
        showFailure()
    }

    private fun showSuccess(joke: Joke) {
        this.resultLiveData.postValue(VisibilityStatus.VISIBLE to Success(joke))
    }

    private fun hideSuccess() {
        this.resultLiveData.postValue(VisibilityStatus.GONE to Success(Joke()))
    }

}