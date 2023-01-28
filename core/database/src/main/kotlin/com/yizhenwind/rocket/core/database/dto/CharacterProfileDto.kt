package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.SectEntity
import com.yizhenwind.rocket.core.database.entity.ServerEntity
import com.yizhenwind.rocket.core.database.entity.ZoneEntity

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
data class CharacterProfileDto(
    val id: Long,
    @ColumnInfo(name = "zone_id")
    val zoneId: Long,
    @Relation(
        parentColumn = "zone_id",
        entityColumn = "id",
    )
    val zone: ZoneEntity,
    @ColumnInfo(name = "server_id")
    val serverId: Long,
    @Relation(
        parentColumn = "server_id",
        entityColumn = "id",
    )
    val server: ServerEntity,
    val name: String,
    @ColumnInfo(name = "sect_id")
    val sectId: Long,
    @Relation(
        parentColumn = "sect_id",
        entityColumn = "id",
    )
    val sect: SectEntity,
    val remark: String?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)