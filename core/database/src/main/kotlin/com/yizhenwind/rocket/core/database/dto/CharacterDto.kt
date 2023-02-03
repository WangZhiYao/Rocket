package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.*

/**
 *
 * @author WangZhiYao
 * @since 2023/1/19
 */
data class CharacterDto(
    @Embedded
    val character: CharacterEntity,
    @Relation(
        entity = ClientEntity::class,
        parentColumn = "client_id",
        entityColumn = "id",
    )
    val client: ClientDto,
    @Relation(
        parentColumn = "zone_id",
        entityColumn = "id",
    )
    val zone: ZoneEntity,
    @Relation(
        parentColumn = "server_id",
        entityColumn = "id",
    )
    val server: ServerEntity,
    @Relation(
        entity = AccountEntity::class,
        parentColumn = "account_id",
        entityColumn = "id",
    )
    val account: AccountDto,
    @Relation(
        parentColumn = "sect_id",
        entityColumn = "id",
    )
    val sect: SectEntity,
    @Relation(
        parentColumn = "internal_id",
        entityColumn = "id",
    )
    val internal: InternalEntity
)