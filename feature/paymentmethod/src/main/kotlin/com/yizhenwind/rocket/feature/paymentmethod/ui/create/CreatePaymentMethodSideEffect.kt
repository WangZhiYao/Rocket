package com.yizhenwind.rocket.feature.paymentmethod.ui.create

import androidx.annotation.StringRes

/**
 *
 * @author WangZhiYao
 * @since 2023/2/24
 */
sealed class CreatePaymentMethodSideEffect {

    data class ShowNameError(@StringRes val resId: Int) : CreatePaymentMethodSideEffect()

    object HideNameError : CreatePaymentMethodSideEffect()

    object CreatePaymentSuccess : CreatePaymentMethodSideEffect()

    data class ShowError(@StringRes val resId: Int) : CreatePaymentMethodSideEffect()

}