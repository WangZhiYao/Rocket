package com.yizhenwind.rocket.core.database.entity

import androidx.room.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/15
 */
@Entity(
    tableName = "account",
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
        Index(value = ["username"], unique = true)
    ]
)
data class AccountEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    /**
     * 客户ID
     */
    @ColumnInfo(name = "client_id", index = true)
    val clientId: Long?,

    /**
     * 用户名
     */
    val username: String,

    /**
     * 密码
     */
    val password: String,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)