package com.yizhenwind.rocket.core.database.mapper

import com.yizhenwind.rocket.core.database.entity.ZoneEntity
import com.yizhenwind.rocket.core.model.Zone
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
class ZoneMapper @Inject constructor() : IEntityMapper<ZoneEntity, Zone> {

    override fun fromEntity(entity: ZoneEntity): Zone =
        entity.run { Zone(id, name) }

    override fun toEntity(model: Zone): ZoneEntity =
        model.run { ZoneEntity(id, name) }

}