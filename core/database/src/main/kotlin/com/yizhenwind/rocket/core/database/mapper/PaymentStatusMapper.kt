package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.PaymentStatusEntity
import com.yizhenwind.rocket.core.model.PaymentStatus
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/27
 */
class PaymentStatusMapper @Inject constructor() :
    IEntityMapper<PaymentStatusEntity, PaymentStatus> {

    override fun fromEntity(entity: PaymentStatusEntity): PaymentStatus =
        entity.run { PaymentStatus(id, name, default, enable) }

    override fun toEntity(model: PaymentStatus): PaymentStatusEntity =
        model.run { PaymentStatusEntity(id, name, default, enable) }

}