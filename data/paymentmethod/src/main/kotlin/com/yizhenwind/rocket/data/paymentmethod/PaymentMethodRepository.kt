package com.yizhenwind.rocket.data.paymentmethod

import com.yizhenwind.rocket.core.database.mapper.EntityListMapper
import com.yizhenwind.rocket.core.database.mapper.PaymentMethodMapper
import com.yizhenwind.rocket.core.model.PaymentMethod
import com.yizhenwind.rocket.data.paymentmethod.source.PaymentMethodLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class PaymentMethodRepository @Inject constructor(
    private val paymentMethodLocalDataSource: PaymentMethodLocalDataSource,
    private val paymentMethodMapper: PaymentMethodMapper
) {

    fun observePaymentMethodList(): Flow<List<PaymentMethod>> =
        paymentMethodLocalDataSource.observePaymentMethodList()
            .map { EntityListMapper(paymentMethodMapper).fromEntity(it) }

    suspend fun getPaymentMethodByName(name: String): PaymentMethod? =
        paymentMethodLocalDataSource.getPaymentMethodByName(name)
            ?.run { paymentMethodMapper.fromEntity(this) }

    fun updatePaymentMethod(paymentMethod: PaymentMethod): Flow<Int> =
        paymentMethodLocalDataSource.updatePaymentMethod(paymentMethodMapper.toEntity(paymentMethod))

    fun createPaymentMethod(paymentMethod: PaymentMethod): Flow<Long> =
        paymentMethodLocalDataSource.createPaymentMethod(paymentMethodMapper.toEntity(paymentMethod))

}