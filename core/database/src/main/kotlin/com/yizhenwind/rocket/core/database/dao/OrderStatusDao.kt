package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.rocket.core.database.entity.OrderStatusEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
@Dao
interface OrderStatusDao : IDao<OrderStatusEntity> {

    @Query("SELECT * FROM order_status WHERE enable = 1")
    fun observeOrderStatus():Flow<List<OrderStatusEntity>>

}