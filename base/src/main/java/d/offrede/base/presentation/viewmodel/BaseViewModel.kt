package d.offrede.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import d.offrede.base.presentation.model.ViewModelResult.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = Main

    protected val jobs = ArrayList<Job>()

    private val failureLiveData: BaseLiveData<Failure> = BaseLiveData()
    private val emptyLiveData: BaseLiveData<Empty> = BaseLiveData()
    private val loadingLiveData: BaseLiveData<Loading> = BaseLiveData()

    fun failureLiveData() = failureLiveData
    fun emptyLiveData() = emptyLiveData
    fun loadingLiveData() = loadingLiveData

    infix fun ArrayList<Job>.add(job: Job) { this.add(job) }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { if(!it.isCancelled) it.cancel() }
    }

    open fun showFailure(message: String = "") {
        hideEmpty()
        hideLoading()
        showFailure(Failure(message))
    }

    open fun hideFailure() {
        hideFailure(Failure())
    }

    open fun showEmpty(message: String = "") {
        hideFailure()
        hideLoading()
        showEmpty(Empty(message))
    }

    open fun hideEmpty() {
        hideEmpty(Empty())
    }

    open fun showLoading(message: String = "") {
        hideFailure()
        hideEmpty()
        showLoading(Loading(message))
    }

    open fun hideLoading() {
        hideLoading(Loading())
    }

    private fun showFailure(failure: Failure) { this.failureLiveData.makeVisible(failure) }

    private fun hideFailure(failure: Failure) { this.failureLiveData.makeGone(failure) }

    private fun showEmpty(empty: Empty) { this.emptyLiveData.makeVisible(empty) }

    private fun hideEmpty(empty: Empty) { this.emptyLiveData.makeGone(empty) }

    private fun showLoading(loading: Loading) { this.loadingLiveData.makeVisible(loading) }

    private fun hideLoading(loading: Loading) { this.loadingLiveData.makeGone(loading) }

}