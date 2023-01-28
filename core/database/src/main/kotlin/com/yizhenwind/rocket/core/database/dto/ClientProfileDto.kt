package com.yizhenwind.rocket.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.yizhenwind.rocket.core.database.entity.ContactTypeEntity
import com.yizhenwind.rocket.core.model.ContactType

/**
 * 客户信息摘要
 *
 * @author WangZhiYao
 * @since 2022/10/30
 */
data class ClientProfileDto(
    val id: Long,
    val name: String,
    @ColumnInfo(name = "contact_type_id")
    val contactTypeId: Long,
    @Relation(
        parentColumn = "contact_type_id",
        entityColumn = "id"
    )
    val contactType: ContactTypeEntity,
    val contact: String,
    @ColumnInfo(name = "account_count")
    val accountCount: Int,
    @ColumnInfo(name = "character_count")
    val characterCount: Int,
    @ColumnInfo(name = "order_count")
    val orderCount: Int,
    @ColumnInfo(name = "create_time")
    val createTime: Long
)
