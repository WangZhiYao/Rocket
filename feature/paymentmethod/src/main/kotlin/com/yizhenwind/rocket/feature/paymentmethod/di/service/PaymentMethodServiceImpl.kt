package com.yizhenwind.rocket.feature.paymentmethod.di.service

import com.yizhenwind.rocket.core.mediator.paymentmethod.IPaymentMethodService
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.domain.paymentmethod.ObservePaymentMethodListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class PaymentMethodServiceImpl @Inject constructor(
    private val observePaymentMethodListUseCase: ObservePaymentMethodListUseCase
) : IPaymentMethodService {

    override fun observePaymentMethodList(): Flow<List<PaymentMethod>> =
        observePaymentMethodListUseCase()

}