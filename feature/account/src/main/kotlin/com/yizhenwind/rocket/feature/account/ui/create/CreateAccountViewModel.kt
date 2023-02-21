package com.yizhenwind.rocket.feature.account.ui.create

import androidx.biometric.BiometricPrompt
import com.yizhenwind.rocket.core.authenticate.usecase.BiometricEncryptUseCase
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.Account
import com.yizhenwind.rocket.core.model.Client
import com.yizhenwind.rocket.core.model.ClientTuple
import com.yizhenwind.rocket.domain.account.CheckPasswordValidUseCase
import com.yizhenwind.rocket.domain.account.CheckUsernameValidUseCase
import com.yizhenwind.rocket.domain.account.CreateAccountUseCase
import com.yizhenwind.rocket.domain.account.GetAccountByUsernameUseCase
import com.yizhenwind.rocket.domain.common.usecase.ClientTupleUseCase
import com.yizhenwind.rocket.domain.common.usecase.TupleContext
import com.yizhenwind.rocket.feature.account.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.math.log

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val clientTupleUseCase: ClientTupleUseCase,
    private val checkUsernameValidUseCase: CheckUsernameValidUseCase,
    private val getAccountByUsernameUseCase: GetAccountByUsernameUseCase,
    private val checkPasswordValidUseCase: CheckPasswordValidUseCase,
    private val biometricEncryptUseCase: BiometricEncryptUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreateAccountViewState, CreateAccountSideEffect>() {

    override val container =
        container<CreateAccountViewState, CreateAccountSideEffect>(CreateAccountViewState())

    fun initViewState(clientId: Long) {
        intent {
            clientTupleUseCase.execute(TupleContext(clientTuple = ClientTuple(id = clientId)))
                .catch {
                    logger.e(it)
                }
                .collect { tupleContext ->
                    tupleContext.apply {
                        reduce {
                            state.copy(
                                clientTupleList = clientTupleList,
                                clientTuple = clientTuple
                            )
                        }
                    }
                }
        }
    }

    fun onClientSelected(clientTuple: ClientTuple) {
        intent {
            reduce {
                state.copy(clientTuple = clientTuple)
            }
        }
    }

    fun onUsernameChanged(username: String?) {
        intent {
            if (username.isNullOrBlank()) {
                postSideEffect(CreateAccountSideEffect.HideAccountError)
                return@intent
            }

            if (getAccountByUsernameUseCase(username) != null) {
                postSideEffect(CreateAccountSideEffect.ShowAccountError(R.string.error_account_exist))
                return@intent
            }

            postSideEffect(CreateAccountSideEffect.HideAccountError)
        }
    }

    fun onPasswordChanged(password: String?) {
        intent {
            postSideEffect(CreateAccountSideEffect.HidePasswordError)
        }
    }

    @OptIn(FlowPreview::class)
    fun createAccount(
        username: String?,
        password: String?,
        authResult: BiometricPrompt.AuthenticationResult? = null
    ) {
        intent {

            if (username.isNullOrBlank()) {
                postSideEffect(CreateAccountSideEffect.ShowAccountError(R.string.error_account_username_empty))
                return@intent
            }

            if (!checkUsernameValidUseCase(username)) {
                postSideEffect(CreateAccountSideEffect.ShowAccountError(R.string.error_account_username_invalid))
                return@intent
            }

            if (getAccountByUsernameUseCase(username) != null) {
                postSideEffect(CreateAccountSideEffect.ShowAccountError(R.string.error_account_exist))
                return@intent
            }

            if (password.isNullOrBlank()) {
                postSideEffect(CreateAccountSideEffect.ShowPasswordError(R.string.error_account_password_empty))
                return@intent
            }

            if (!checkPasswordValidUseCase(password)) {
                postSideEffect(CreateAccountSideEffect.ShowPasswordError(R.string.error_account_password_invalid))
                return@intent
            }

            val account =
                Account(
                    client = Client(id = state.clientTuple.id),
                    username = username,
                    password = password
                )

            if (authResult != null) {
                biometricEncryptUseCase(authResult, password)
                    .map { cipherData ->
                        cipherData.run {
                            account.copy(
                                password = ciphertext,
                                iv = iv,
                                encrypted = true
                            )
                        }
                    }
                    .catch {
                        logger.e(it)
                        emit(account)
                    }
                    .flatMapConcat {
                        createAccountUseCase(it)
                    }
                    .collect {
                        postSideEffect(CreateAccountSideEffect.CreateAccountSuccess(it))
                    }
            } else {
                createAccountUseCase(account)
                    .collect {
                        postSideEffect(CreateAccountSideEffect.CreateAccountSuccess(it))
                    }
            }
        }
    }
}