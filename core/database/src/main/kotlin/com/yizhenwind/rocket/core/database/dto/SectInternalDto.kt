package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.InternalEntity
import com.yizhenwind.rocket.core.database.entity.SectEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class SectInternalDto(
    @Embedded
    val sectEntity: SectEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "sect_id"
    )
    val internalEntityList: List<InternalEntity>
)