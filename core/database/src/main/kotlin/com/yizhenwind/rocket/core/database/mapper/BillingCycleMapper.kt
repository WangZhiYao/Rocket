package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.BillingCycleEntity
import com.yizhenwind.rocket.core.model.BillingCycle
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class BillingCycleMapper @Inject constructor() : IEntityMapper<BillingCycleEntity, BillingCycle> {

    override fun fromEntity(entity: BillingCycleEntity): BillingCycle =
        entity.run { BillingCycle(id, name, default, enable) }

    override fun toEntity(model: BillingCycle): BillingCycleEntity =
        model.run { BillingCycleEntity(id, name, default, enable) }

}