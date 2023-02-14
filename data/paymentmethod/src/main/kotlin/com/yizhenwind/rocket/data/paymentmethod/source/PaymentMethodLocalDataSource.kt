package com.yizhenwind.rocket.data.paymentmethod.source

import com.yizhenwind.rocket.core.database.dao.PaymentMethodDao
import com.yizhenwind.rocket.core.database.entity.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow
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

}