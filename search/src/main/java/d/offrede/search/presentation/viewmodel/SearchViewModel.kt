package d.offrede.search.presentation.viewmodel

import android.util.Log
import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.base.presentation.model.ViewModelResult
import d.offrede.base.presentation.model.VisibilityStatus
import d.offrede.base.presentation.viewmodel.BaseLiveData
import d.offrede.base.presentation.viewmodel.BaseViewModel
import d.offrede.search.domain.model.SearchResultDomain
import d.offrede.search.presentation.mapper.SearchResultDomainToView
import d.offrede.search.presentation.model.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchViewModel(
    private val searchUseCase: BaseUseCase<SearchResultDomain, String>
) : BaseViewModel() {

    private val resultLiveData = BaseLiveData<ViewModelResult.Success<SearchResult>>()

    fun resultLiveData() = resultLiveData

    fun search(query: String) {
        jobs add launch {
            showLoading()
            withContext(Dispatchers.IO) {
                searchUseCase.run(
                    query,
                    onSuccess = ::makeSuccess,
                    onFailure = ::makeFailure
                )
            }
            hideLoading()
        }
    }

    private fun makeSuccess(result: SearchResultDomain) {
        showSuccess(SearchResultDomainToView.transform(result))
        hideEmpty()
        hideLoading()
        hideFailure()
    }

    private fun makeFailure(failureResult: Exception) {
        Log.e("failureResult", failureResult.message ?: "", failureResult)
        showFailure()
    }

    private fun showSuccess(searchResult: SearchResult) {
        this.resultLiveData.postValue(VisibilityStatus.VISIBLE to ViewModelResult.Success(searchResult))
    }

    private fun hideSuccess() {
        this.resultLiveData.postValue(VisibilityStatus.GONE to ViewModelResult.Success(SearchResult()))
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