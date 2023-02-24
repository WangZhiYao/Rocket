package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface PaymentMethodDao : IDao<PaymentMethodEntity> {

    @Query("SELECT * FROM payment_method WHERE enable = 1")
    fun observePaymentMethodList(): Flow<List<PaymentMethodEntity>>

    @Query("SELECT * FROM payment_method WHERE name = :name")
    suspend fun getPaymentMethodByName(name: String): PaymentMethodEntity?

}