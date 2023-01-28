package com.yizhenwind.rocket.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.rocket.core.database.dto.OrderProfileDto
import com.yizhenwind.rocket.core.database.entity.OrderEntity

/**
 * 订单表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface OrderDao : IDao<OrderEntity> {

    @Transaction
    @Query(
        """
            SELECT
              id,
              subject_id,
              remark,
              order_status_id,
              payment_status_id,
              create_time
            FROM 
              `order`
            WHERE
              client_id = :clientId
              AND enable = 1
            ORDER BY
              create_time DESC
        """
    )
    fun observeOrderProfileByClientId(clientId: Long): PagingSource<Int, OrderProfileDto>

}