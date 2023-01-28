package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.PaymentStatusEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface PaymentStatusDao : IDao<PaymentStatusEntity> {

    @Query("SELECT * FROM payment_status WHERE enable = 1")
    fun observePaymentStatus(): Flow<List<PaymentStatusEntity>>

}