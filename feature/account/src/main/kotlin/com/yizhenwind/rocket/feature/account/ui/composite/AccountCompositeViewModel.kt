package com.yizhenwind.rocket.feature.account.ui.composite

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.domain.account.DeleteAccountUseCase
import com.yizhenwind.rocket.feature.account.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@HiltViewModel
class AccountCompositeViewModel @Inject constructor(
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<AccountCompositeViewState, AccountCompositeSideEffect>() {

    override val container =
        container<AccountCompositeViewState, AccountCompositeSideEffect>(AccountCompositeViewState())

    val account: Account
        get() = container.stateFlow.value.account

    fun setAccount(account: Account) {
        intent {
            reduce {
                state.copy(account = account)
            }
        }
    }

    fun showError(@StringRes resId: Int) {
        intent {
            postSideEffect(AccountCompositeSideEffect.ShowError(resId))
        }
    }

    fun deleteAccount(account: Account) {
        intent {
            deleteAccountUseCase(account)
                .catch {
                    logger.e(it)
                    postSideEffect(AccountCompositeSideEffect.ShowError(R.string.error_delete_account_failed))
                }
                .collect {
                    postSideEffect(AccountCompositeSideEffect.DeleteAccountSuccess)
                }
        }
    }
}