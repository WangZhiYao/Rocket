package com.yizhenwind.rocket.domain.paymentmethod

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.data.paymentmethod.PaymentMethodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class ObservePaymentMethodListUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<PaymentMethod>> =
        paymentMethodRepository.observePaymentMethodList().flowOn(dispatcher)

}