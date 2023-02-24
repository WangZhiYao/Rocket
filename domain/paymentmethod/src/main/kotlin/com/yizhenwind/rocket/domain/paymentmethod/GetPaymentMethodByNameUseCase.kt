package com.yizhenwind.rocket.domain.paymentmethod

import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.data.paymentmethod.PaymentMethodRepository
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/24
 */
class GetPaymentMethodByNameUseCase @Inject constructor(
    private val paymentMethodRepository: PaymentMethodRepository
) {

    suspend operator fun invoke(name: String): PaymentMethod =
        paymentMethodRepository.getPaymentMethodByName(name) ?: PaymentMethod()

}