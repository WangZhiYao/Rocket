package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.PeriodEntity
import com.yizhenwind.rocket.core.model.Period
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/28
 */
class PeriodMapper @Inject constructor() : IEntityMapper<PeriodEntity, Period> {

    override fun fromEntity(entity: PeriodEntity): Period =
        entity.run { Period(id, value, default, enable) }

    override fun toEntity(model: Period): PeriodEntity =
        model.run { PeriodEntity(id, value, default, enable) }

}