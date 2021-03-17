package d.offrede.base.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import d.offrede.base.R
import d.offrede.base.databinding.FragmentContainerBinding
import d.offrede.base.presentation.extension.gone
import d.offrede.base.presentation.extension.invisible
import d.offrede.base.presentation.extension.visible
import d.offrede.base.presentation.viewmodel.BaseViewModel

abstract class BaseFragment : Fragment(R.layout.fragment_container) {

    @LayoutRes
    abstract fun layoutId(): Int

    open lateinit var baseBinding: FragmentContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseBinding = FragmentContainerBinding.bind(view)
        layoutInflater.inflate(layoutId(), baseBinding.fragContainer, true)
        startObserves()
    }

    open fun baseViewModel(): BaseViewModel? = null

    open fun observeFailure() {
        baseViewModel()?.failureLiveData()?.observe(this, {
            baseBinding.fragmentFailureContainer.failureText.text = it.message
            baseBinding.fragmentFailureContainer.root.visible()
        }, {
            baseBinding.fragmentFailureContainer.root.invisible()
        }, {
            baseBinding.fragmentFailureContainer.root.gone()
        })
    }

    open fun observeEmpty() {
        baseViewModel()?.emptyLiveData()?.observe(this, {
            baseBinding.fragmentEmptyContainer.emptyText.text = it.message
            baseBinding.fragmentEmptyContainer.root.visible()
        }, {
            baseBinding.fragmentEmptyContainer.root.invisible()
        }, {
            baseBinding.fragmentEmptyContainer.root.gone()
        })
    }

    open fun observeLoading() {
        baseViewModel()?.loadingLiveData()?.observe(this, {
            baseBinding.fragmentLoadingContainer.progressText.text = it.message
            baseBinding.fragmentLoadingContainer.root.visible()
        }, {
            baseBinding.fragmentLoadingContainer.root.invisible()
        }, {
            baseBinding.fragmentLoadingContainer.root.gone()
        })
    }

    private fun startObserves() {
        observeLoading()
        observeFailure()
        observeEmpty()
    }
}