package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.common.mapper.IMapper
import com.yizhenwind.rocket.core.database.dto.OrderProfileDto
import com.yizhenwind.rocket.core.model.OrderProfile
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/22
 */
class OrderProfileDtoMapper @Inject constructor(
    private val subjectDtoMapper: SubjectDtoMapper,
    private val orderStatusMapper: OrderStatusMapper,
    private val paymentStatusMapper: PaymentStatusMapper
) : IMapper<OrderProfileDto, OrderProfile> {

    override fun map(input: OrderProfileDto): OrderProfile =
        input.run {
            OrderProfile(
                id,
                subjectDtoMapper.map(subject),
                remark,
                orderStatusMapper.fromEntity(orderStatus),
                paymentStatusMapper.fromEntity(paymentStatus),
                createTime
            )
        }

}