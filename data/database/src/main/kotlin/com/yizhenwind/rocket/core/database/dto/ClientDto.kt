package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import com.yizhenwind.rocket.core.database.entity.ContactEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/7
 */
data class ClientDto(
    @Embedded
    val client: ClientEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "client_id"
    )
    val contactList: List<ContactEntity>
)