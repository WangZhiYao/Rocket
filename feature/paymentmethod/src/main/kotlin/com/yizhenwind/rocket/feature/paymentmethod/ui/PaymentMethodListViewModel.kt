package com.yizhenwind.rocket.feature.paymentmethod.ui

import com.yizhenwind.rocket.core.framework.base.BaseMVIViewModel
import com.yizhenwind.rocket.domain.paymentmethod.ObservePaymentMethodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
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
    private val observePaymentMethodListUseCase: ObservePaymentMethodListUseCase
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
}