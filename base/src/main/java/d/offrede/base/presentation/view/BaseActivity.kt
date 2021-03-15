package d.offrede.base.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import d.offrede.base.databinding.ActivityContainerBinding
import d.offrede.base.presentation.extension.gone
import d.offrede.base.presentation.extension.invisible
import d.offrede.base.presentation.extension.visible
import d.offrede.base.presentation.viewmodel.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {

    lateinit var baseBinding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = ActivityContainerBinding.inflate(layoutInflater)
        super.setContentView(baseBinding.root)
        setSupportActionBar(baseBinding.toolbar)
        startObserves()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setContentView(layoutResId: Int) {
        layoutInflater.inflate(layoutResId, baseBinding.activityContainer, true)
    }

    private fun startObserves() {
        observeLoading()
        observeFailure()
        observeEmpty()
    }

    open fun baseViewModel(): BaseViewModel? = null

    private fun observeLoading() {
        baseViewModel()?.loadingLiveData()?.observe(this, {
            baseBinding.loadingContainer.progressText.text = it.message
            baseBinding.loadingContainer.root.visible()
        }, {
            baseBinding.loadingContainer.root.invisible()
        }, {
            baseBinding.loadingContainer.root.gone()
        })
    }

    private fun observeFailure() {
        baseViewModel()?.failureLiveData()?.observe(this, {
            baseBinding.failureContainer.failureText.text = it.message
            baseBinding.failureContainer.root.visible()
        }, {
            baseBinding.failureContainer.root.invisible()
        }, {
            baseBinding.failureContainer.root.gone()
        })
    }

    private fun observeEmpty() {
        baseViewModel()?.emptyLiveData()?.observe(this, {
            baseBinding.emptyContainer.emptyText.text = it.message
            baseBinding.emptyContainer.root.visible()
        }, {
            baseBinding.emptyContainer.root.invisible()
        }, {
            baseBinding.emptyContainer.root.gone()
        })
    }

    protected fun showToast(@StringRes resMessage: Int) {
        showToast(getString(resMessage))
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}