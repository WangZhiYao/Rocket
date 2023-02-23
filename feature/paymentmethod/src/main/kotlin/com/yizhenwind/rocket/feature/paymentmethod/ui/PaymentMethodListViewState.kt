package com.yizhenwind.rocket.feature.paymentmethod.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.PaymentMethod

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
data class PaymentMethodListViewState(
    val paymentMethodList: List<PaymentMethod> = emptyList()
) : IViewState