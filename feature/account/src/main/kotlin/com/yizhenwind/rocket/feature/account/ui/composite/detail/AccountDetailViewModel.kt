package com.yizhenwind.rocket.feature.account.ui.composite.detail

import androidx.biometric.BiometricPrompt
import com.yizhenwind.rocket.core.authenticate.usecase.BiometricDecryptUseCase
import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.domain.account.ObserveAccountByIdUseCase
import com.yizhenwind.rocket.feature.account.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val observeAccountByIdUseCase: ObserveAccountByIdUseCase,
    private val biometricDecryptUseCase: BiometricDecryptUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<AccountDetailViewState, AccountDetailSideEffect>() {

    override val container =
        container<AccountDetailViewState, AccountDetailSideEffect>(AccountDetailViewState())

    val account: Account
        get() = container.stateFlow.value.account

    val passwordDecrypted: Boolean
        get() = container.stateFlow.value.passwordDecrypt

    fun observeAccountById(id: Long) {
        intent {
            observeAccountByIdUseCase(id)
                .collect { account ->
                    if (account.id == Constant.DEFAULT_ID) {
                        postSideEffect(AccountDetailSideEffect(R.string.error_account_detail))
                    } else {
                        reduce {
                            state.copy(account = account, passwordDecrypt = false)
                        }
                    }
                }
        }
    }

    fun decryptPassword(authResult: BiometricPrompt.AuthenticationResult) {
        intent {
            biometricDecryptUseCase(authResult, account.password)
                .catch {
                    logger.e(it)
                    postSideEffect(AccountDetailSideEffect(R.string.error_account_detail_password_decrypt))
                }
                .map { password ->
                    account.copy(password = password, encrypted = false)
                }
                .collect { account ->
                    reduce {
                        state.copy(account = account, passwordDecrypt = true)
                    }
                }
        }
    }
}