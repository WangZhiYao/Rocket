package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity

/**
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class ClientTupleDto(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "contact_type_id")
    val contactTypeId: Long,
    @Relation(
        parentColumn = "contact_type_id",
        entityColumn = "id"
    )
    val contactTypeEntity: ContactTypeEntity,
    val contact: String,
)