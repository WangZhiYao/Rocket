package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.AccountEntity
import com.yizhenwind.rocket.core.database.entity.ClientEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/2
 */
data class AccountDto(
    @Embedded
    val accountEntity: AccountEntity,
    @Relation(
        parentColumn = "client_id",
        entityColumn = "id"
    )
    val clientEntity: ClientEntity
)