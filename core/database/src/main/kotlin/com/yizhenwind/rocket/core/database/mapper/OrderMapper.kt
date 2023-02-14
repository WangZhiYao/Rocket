package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.entity.OrderEntity
import com.yizhenwind.rocket.core.model.Order
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/12
 */
class OrderMapper @Inject constructor() : IMapper<Order, OrderEntity> {

    override fun map(input: Order): OrderEntity =
        input.run {
            OrderEntity(
                id,
                client.id,
                account.id,
                character.id,
                category.id,
                subject.id,
                startTime,
                endTime,
                totalAmount,
                orderStatus,
                statusUpdateTime,
                paymentStatus,
                paymentMethod.id,
                paymentTime,
                paymentAmount,
                refundAmount,
                refundTime,
                remark,
                enable,
                createTime
            )
        }
}