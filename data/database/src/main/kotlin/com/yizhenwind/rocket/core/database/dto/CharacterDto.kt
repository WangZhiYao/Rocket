package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.*

/**
 *
 * @author WangZhiYao
 * @since 2023/1/4
 */
data class CharacterDto(
    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = "zone_id",
        entityColumn = "id"
    )
    val zoneEntity: ZoneEntity,

    @Relation(
        parentColumn = "server_id",
        entityColumn = "id"
    )
    val serverEntity: ServerEntity,

    @Relation(
        parentColumn = "sect_id",
        entityColumn = "id"
    )
    val sectEntity: SectEntity,

    @Relation(
        parentColumn = "internal_id",
        entityColumn = "id"
    )
    val internalEntity: InternalEntity
)