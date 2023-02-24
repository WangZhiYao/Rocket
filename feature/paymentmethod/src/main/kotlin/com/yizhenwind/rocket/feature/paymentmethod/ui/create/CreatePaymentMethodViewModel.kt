package com.yizhenwind.rocket.feature.paymentmethod.ui.create

import com.yizhenwind.rocket.core.common.constant.Constant
import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.domain.paymentmethod.CreatePaymentMethodUseCase
import com.yizhenwind.rocket.domain.paymentmethod.GetPaymentMethodByNameUseCase
import com.yizhenwind.rocket.domain.paymentmethod.UpdatePaymentMethodUseCase
import com.yizhenwind.rocket.feature.paymentmethod.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/24
 */
@HiltViewModel
class CreatePaymentMethodViewModel @Inject constructor(
    private val getPaymentMethodByNameUseCase: GetPaymentMethodByNameUseCase,
    private val createPaymentMethodUseCase: CreatePaymentMethodUseCase,
    private val updatePaymentMethodUseCase: UpdatePaymentMethodUseCase,
    private val logger: ILogger
) : BaseMVIViewModel<CreatePaymentMethodViewState, CreatePaymentMethodSideEffect>() {

    override val container =
        container<CreatePaymentMethodViewState, CreatePaymentMethodSideEffect>(
            CreatePaymentMethodViewState()
        )

    fun onNameChanged(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreatePaymentMethodSideEffect.HideNameError)
                return@intent
            }

            val paymentMethod = getPaymentMethodByNameUseCase(name)
            if (paymentMethod.id != Constant.DEFAULT_ID && paymentMethod.enable) {
                postSideEffect(CreatePaymentMethodSideEffect.ShowNameError(R.string.error_payment_method_exist))
                return@intent
            }

            postSideEffect(CreatePaymentMethodSideEffect.HideNameError)
        }
    }

    fun createPaymentMethod(name: String?) {
        intent {
            if (name.isNullOrBlank()) {
                postSideEffect(CreatePaymentMethodSideEffect.ShowNameError(R.string.error_create_payment_method_empty_name))
                return@intent
            }

            val paymentMethod = getPaymentMethodByNameUseCase(name)
            if (paymentMethod.id != Constant.DEFAULT_ID && paymentMethod.enable) {
                postSideEffect(CreatePaymentMethodSideEffect.ShowNameError(R.string.error_payment_method_exist))
                return@intent
            }

            val upsertFlow = if (paymentMethod.id == Constant.DEFAULT_ID) {
                createPaymentMethodUseCase(PaymentMethod(name = name))
            } else if (!paymentMethod.enable) {
                updatePaymentMethodUseCase(paymentMethod.copy(enable = true))
            } else {
                flowOf(PaymentMethod())
            }

            upsertFlow
                .catch {
                    logger.e(it)
                    emit(PaymentMethod())
                }
                .collect {
                    if (it.id == Constant.DEFAULT_ID) {
                        postSideEffect(CreatePaymentMethodSideEffect.ShowError(R.string.error_create_payment_method_failed))
                    } else {
                        postSideEffect(CreatePaymentMethodSideEffect.CreatePaymentSuccess)
                    }
                }
        }
    }
}