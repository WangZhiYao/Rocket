package com.yizhenwind.rocket.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.ClientEntity
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
data class ClientDto(
    @Embedded
    val client: ClientEntity,
    @Relation(
        parentColumn = "contact_type_id",
        entityColumn = "id"
    )
    val contactType: ContactTypeEntity
)