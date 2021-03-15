package d.offrede.base.domain.usecase

import androidx.annotation.StringRes
import d.offrede.base.R.string.connection_failure_network as failureNetwork
import d.offrede.base.R.string.connection_failure_unexpected as failureUnexpected

sealed class BaseFailureResult(@StringRes val resMessage: Int) {
    object Unexpected : BaseFailureResult(failureUnexpected)
    object Network : BaseFailureResult(failureNetwork)
}