package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.ServerEntity
import com.yizhenwind.rocket.core.database.entity.ZoneEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class ZoneServerDto(
    @Embedded
    val zoneEntity: ZoneEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "zone_id"
    )
    val serverEntityList: List<ServerEntity>
)