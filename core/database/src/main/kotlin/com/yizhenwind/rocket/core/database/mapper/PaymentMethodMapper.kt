package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.PaymentMethodEntity
import com.yizhenwind.rocket.core.model.PaymentMethod
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class PaymentMethodMapper @Inject constructor() :
    IEntityMapper<PaymentMethodEntity, PaymentMethod> {

    override fun fromEntity(entity: PaymentMethodEntity): PaymentMethod =
        entity.run { PaymentMethod(id, name, default, enable) }

    override fun toEntity(model: PaymentMethod): PaymentMethodEntity =
        model.run { PaymentMethodEntity(id, name, default, enable) }

}