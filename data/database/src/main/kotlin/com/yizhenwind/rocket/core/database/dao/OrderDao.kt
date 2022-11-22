package com.yizhenwind.rocket.core.database.dao

import androidx.room.Dao
import com.yizhenwind.rocket.core.database.entity.OrderEntity

/**
 * 订单表操作
 *
 * @author WangZhiYao
 * @since 2021/10/27
 */
@Dao
interface OrderDao : IDao<OrderEntity>