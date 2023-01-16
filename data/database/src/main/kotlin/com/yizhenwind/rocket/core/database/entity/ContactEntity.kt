package com.yizhenwind.rocket.core.database.entity

import androidx.room.*
import com.yizhenwind.rocket.core.common.constant.ContactType

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/2
 */
@Entity(
    tableName = "contact",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["client_id"]),
        Index(value = ["type", "value"], unique = true)
    ]
)
data class ContactEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    /**
     * 客户ID
     */
    @ColumnInfo(name = "client_id")
    val clientId: Long,

    /**
     * 联系方式类型
     */
    val type: ContactType,

    /**
     * 联系方式
     */
    val value: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)