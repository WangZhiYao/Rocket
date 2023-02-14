package com.yizhenwind.rocket.core.database.dto.simple

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.AccountEntity
import com.yizhenwind.rocket.core.database.entity.SectEntity
import com.yizhenwind.rocket.core.database.entity.ServerEntity
import com.yizhenwind.rocket.core.database.entity.ZoneEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
data class SimpleCharacterDto(
    val id: Long,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @Relation(
        entity = AccountEntity::class,
        parentColumn = "account_id",
        entityColumn = "id",
    )
    val simpleAccountDto: SimpleAccountDto,
    @ColumnInfo(name = "zone_id")
    val zoneId: Long,
    @Relation(
        parentColumn = "zone_id",
        entityColumn = "id",
    )
    val zoneEntity: ZoneEntity,
    @ColumnInfo(name = "server_id")
    val serverId: Long,
    @Relation(
        parentColumn = "server_id",
        entityColumn = "id",
    )
    val serverEntity: ServerEntity,
    val name: String,
    @ColumnInfo(name = "sect_id")
    val sectId: Long,
    @Relation(
        parentColumn = "sect_id",
        entityColumn = "id",
    )
    val sectEntity: SectEntity
)