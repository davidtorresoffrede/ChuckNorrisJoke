package d.offrede.categories.presentation.viewmodel

import android.util.Log
import d.offrede.base.domain.usecase.BaseUseCase
import d.offrede.base.presentation.model.ViewModelResult
import d.offrede.base.presentation.model.VisibilityStatus
import d.offrede.base.presentation.viewmodel.BaseLiveData
import d.offrede.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel(
    private val categoriesUseCase: BaseUseCase<List<String>, Nothing>
) : BaseViewModel() {

    private val resultLiveData = BaseLiveData<ViewModelResult.Success<List<String>>>()

    fun resultLiveData() = resultLiveData

    fun getCategories() {
        jobs add launch {
            showLoading()
            withContext(Dispatchers.IO) {
                categoriesUseCase.run(
                    onSuccess = ::makeSuccess,
                    onFailure = ::makeFailure
                )
            }
            hideLoading()
        }
    }

    private fun makeSuccess(result: List<String>) {
        showSuccess(result)
        hideEmpty()
        hideLoading()
        hideFailure()
    }

    private fun makeFailure(failureResult: Exception) {
        Log.e("failureResult", failureResult.message ?: "", failureResult)
        showFailure()
    }

    private fun showSuccess(list: List<String>) {
        this.resultLiveData.postValue(VisibilityStatus.VISIBLE to ViewModelResult.Success(list))
    }

    private fun hideSuccess() {
        this.resultLiveData.postValue(VisibilityStatus.GONE to ViewModelResult.Success(listOf()))
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