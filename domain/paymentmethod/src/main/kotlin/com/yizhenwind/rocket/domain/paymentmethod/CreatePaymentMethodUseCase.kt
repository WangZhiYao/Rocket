package com.yizhenwind.rocket.domain.paymentmethod

import com.yizhenwind.rocket.core.common.di.qualifier.coroutine.IODispatcher
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.data.paymentmethod.PaymentMethodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/24
 */
class CreatePaymentMethodUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(paymentMethod: PaymentMethod): Flow<PaymentMethod> =
        paymentMethodRepository.createPaymentMethod(paymentMethod)
            .map { id -> paymentMethod.copy(id = id) }
            .flowOn(dispatcher)

}