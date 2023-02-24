package com.yizhenwind.rocket.feature.paymentmethod.ui

import com.yizhenwind.rocket.core.model.PaymentMethod

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
sealed class PaymentMethodListSideEffect {

    data class DeletePaymentMethodSuccess(val paymentMethod: PaymentMethod) :
        PaymentMethodListSideEffect()

}