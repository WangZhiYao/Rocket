package com.yizhenwind.rocket.data.paymentmethod.source

import com.yizhenwind.rocket.core.database.dao.PaymentMethodDao
import com.yizhenwind.rocket.core.database.entity.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
class PaymentMethodLocalDataSource @Inject constructor(
    private val paymentMethodDao: PaymentMethodDao
) {

    fun observePaymentMethodList(): Flow<List<PaymentMethodEntity>> =
        paymentMethodDao.observePaymentMethodList()

    suspend fun getPaymentMethodByName(name: String): PaymentMethodEntity? =
        paymentMethodDao.getPaymentMethodByName(name)

    fun updatePaymentMethod(paymentMethodEntity: PaymentMethodEntity): Flow<Int> =
        flow {
            emit(paymentMethodDao.update(paymentMethodEntity))
        }

    fun createPaymentMethod(paymentMethodEntity: PaymentMethodEntity): Flow<Long> =
        flow {
            emit(paymentMethodDao.insert(paymentMethodEntity))
        }

}