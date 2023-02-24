package com.yizhenwind.rocket.feature.paymentmethod.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.domain.paymentmethod.ObservePaymentMethodListUseCase
import com.yizhenwind.rocket.domain.paymentmethod.UpdatePaymentMethodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
@HiltViewModel
class PaymentMethodListViewModel @Inject constructor(
    private val observePaymentMethodListUseCase: ObservePaymentMethodListUseCase,
    private val updatePaymentMethodUseCase: UpdatePaymentMethodUseCase
) : BaseMVIViewModel<PaymentMethodListViewState, PaymentMethodListSideEffect>() {

    override val container =
        container<PaymentMethodListViewState, PaymentMethodListSideEffect>(
            PaymentMethodListViewState()
        )

    init {
        intent {
            observePaymentMethodListUseCase()
                .collect { paymentMethodList ->
                    reduce {
                        state.copy(paymentMethodList = paymentMethodList)
                    }
                }
        }
    }

    fun updatePaymentMethod(paymentMethod: PaymentMethod) {
        intent {
            updatePaymentMethodUseCase(paymentMethod)
                .collect { paymentMethod ->
                    if (!paymentMethod.enable) {
                        postSideEffect(
                            PaymentMethodListSideEffect.DeletePaymentMethodSuccess(
                                paymentMethod
                            )
                        )
                    }
                }
        }
    }
}