package d.offrede.base.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import d.offrede.base.presentation.model.ViewModelResult
import d.offrede.base.presentation.model.VisibilityStatus
import d.offrede.base.presentation.model.VisibilityStatus.*

class BaseLiveData<T : ViewModelResult<Any>> : MutableLiveData<Pair<VisibilityStatus, T>>() {

    fun observe(
            owner: LifecycleOwner,
            onVisible: (T) -> Unit = {},
            onInvisible: (T) -> Unit = {},
            onGone: (T) -> Unit = {}
    ) {
        super.observe(
                owner,
                {
                    make(
                        it,
                        onVisible,
                        onInvisible,
                        onGone
                    )
                }
        )
    }

    fun makeVisible(result: T) {
        this.postValue(VISIBLE to result)
    }

    fun makeInvisible(result: T) {
        this.postValue(INVISIBLE to result)
    }

    fun makeGone(result: T) {
        this.postValue(GONE to result)
    }

    private fun make(
        data: Pair<VisibilityStatus, T>,
        onVisible: (T) -> Unit = {},
        onInvisible: (T) -> Unit = {},
        onGone: (T) -> Unit = {}
    ) {
        when (data.first) {
            VISIBLE -> onVisible(data.second)
            INVISIBLE -> onInvisible(data.second)
            GONE -> onGone(data.second)
        }
    }

}