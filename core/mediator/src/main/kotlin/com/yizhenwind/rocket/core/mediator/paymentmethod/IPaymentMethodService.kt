package com.yizhenwind.rocket.core.mediator.paymentmethod

import com.yizhenwind.rocket.core.model.PaymentMethod
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
interface IPaymentMethodService {

    fun observePaymentMethodList(): Flow<List<PaymentMethod>>

}