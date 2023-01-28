package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.OrderStatusEntity
import com.yizhenwind.rocket.core.model.OrderStatus
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/27
 */
class OrderStatusMapper @Inject constructor() : IEntityMapper<OrderStatusEntity, OrderStatus> {

    override fun fromEntity(entity: OrderStatusEntity): OrderStatus =
        entity.run { OrderStatus(id, name, default, enable) }

    override fun toEntity(model: OrderStatus): OrderStatusEntity =
        model.run { OrderStatusEntity(id, name, default, enable) }

}