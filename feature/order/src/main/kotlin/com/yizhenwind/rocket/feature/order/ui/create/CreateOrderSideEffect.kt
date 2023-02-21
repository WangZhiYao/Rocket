package com.yizhenwind.rocket.feature.order.ui.create

import androidx.annotation.StringRes
import com.yizhenwind.rocket.core.model.Order

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
sealed class CreateOrderSideEffect {

    data class ShowClientError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideClientError : CreateOrderSideEffect()

    data class ShowAccountError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideAccountError : CreateOrderSideEffect()

    data class ShowCharacterError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideCharacterError : CreateOrderSideEffect()

    data class ShowCategoryError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideCategoryError : CreateOrderSideEffect()

    data class ShowSubjectError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideSubjectError : CreateOrderSideEffect()

    data class ShowTotalAmountError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideTotalAmountError : CreateOrderSideEffect()

    data class ShowTotalAmountHelper(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HideTotalAmountHelper : CreateOrderSideEffect()

    data class ShowPaymentMethodError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HidePaymentMethodError : CreateOrderSideEffect()

    data class ShowPaymentAmountError(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HidePaymentAmountError : CreateOrderSideEffect()

    data class ShowPaymentAmountHelper(@StringRes val resId: Int) : CreateOrderSideEffect()

    object HidePaymentAmountHelper : CreateOrderSideEffect()

    data class CreateOrderSuccess(val order: Order) : CreateOrderSideEffect()

    data class CreateOrderFailed(@StringRes val resId: Int) : CreateOrderSideEffect()

}